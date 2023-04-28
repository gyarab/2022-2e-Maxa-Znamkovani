package com.example.zkouskaprihlaseni3;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PrihlaseniController implements Initializable {

    @FXML
    private Button prihlasit;
    @FXML
    private Button button_zmenitHeslo;
    @FXML
    private Label label_ucitel;
    @FXML
    private Label label_spatneHeslo;
    @FXML
    private TextField tf_password;
    @FXML
    private ComboBox<Ucitel> cbox_ucitele;


    String ucitel;
    static String ucitelUsernamePrihlaseni;
    static String UcitelPoVybrani;
    ObservableList<Ucitel> ucitele = FXCollections.observableArrayList();
    ArrayList<Ucitel> uciteleArr = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //nacte vsechny ucitele(krestni, prijmeni, username, password) z databaze
        //to pak ulozi do arraylistu uciteleArr a ObservableListu ucitele
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT ucitel_krestni,ucitel_prijmeni,username,password FROM ucitele");
            while (resultSet.next()) {
                String krestni = resultSet.getString("ucitel_krestni");
                String prijmeni = resultSet.getString("ucitel_prijmeni");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                //ObservalList ktery slouzi jen pro vlozeni ucitelu do ComoboBoxu cbox_ucitele
                ucitele.add(new Ucitel(krestni, prijmeni, username, password));
                //ArrayListu ucitelu, podle ktereho budeme overovat prihlasovaci udaje
                uciteleArr.add(new Ucitel(krestni, prijmeni, username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cbox_ucitele.setItems(ucitele);

        //nastavi promennou pro username, kterou pak pouzivaji ostatni tridy
        cbox_ucitele.setOnAction(actionEvent -> {
            ucitel = String.valueOf(cbox_ucitele.getValue());
            UcitelPoVybrani = ucitel;
            label_ucitel.setText(ucitel);
            for (int i = 0; i < uciteleArr.size(); i++) {
                if (ucitel.equals(uciteleArr.get(i).krestni + " " + uciteleArr.get(i).prijmeni)) {
                    ucitelUsernamePrihlaseni = String.valueOf(uciteleArr.get(i).username);
                }
            }

        });
    //tlacitko prihlasit se
        prihlasit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //prvni for je pro to aby nasel vybraneho ucitele z arraylistu
                //druhy for je pro overeni hesla, kdyz je heslo spravne, program ucitele prihlasi = presune ho do sceny znamkovani
                // a nastavi promennou pro username, kterou pak pouzivaji ostatni tridy
                for (int i = 0; i < uciteleArr.size(); i++) {
                    if (ucitel.equals(uciteleArr.get(i).krestni + " " + uciteleArr.get(i).prijmeni)) {
                        if (uciteleArr.get(i).password.equals(tf_password.getText())) {
                            ucitelUsernamePrihlaseni = String.valueOf(uciteleArr.get(i).username);
                            DBUtils.changeScene(event, "znamkovani.fxml", "Známkování"+"-"+ucitelUsernamePrihlaseni);
                        } else {
                            label_spatneHeslo.setText("Chybné heslo");
                        }
                    }
                }
            }
        });

    //tlacitko zmenit heslo - jeho aktivaci program ucitele presnu na scenu pro zmenu hesla
        button_zmenitHeslo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DBUtils.changeScene(actionEvent, "zmenaHesla.fxml", "Změna hesla"+"-"+ucitelUsernamePrihlaseni);
            }
        });
    }
}

