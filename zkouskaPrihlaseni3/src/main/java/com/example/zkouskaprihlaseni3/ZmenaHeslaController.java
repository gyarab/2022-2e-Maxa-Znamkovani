package com.example.zkouskaprihlaseni3;

import connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ZmenaHeslaController implements Initializable {
    @FXML
    private TextField tf_stareHeslo;
    @FXML
    private TextField tf_noveHeslo;
    @FXML
    private TextField tf_noveHesloOvereni;
    @FXML
    private Button button_Zmenit;
    @FXML
    private Button button_prihlasitSe;
    @FXML
    private Label label_chybaStareHeslo;
    @FXML
    private Label label_uspech;
    @FXML
    private Label label_chybaNoveHeslo;
    @FXML
    private Label label_notUsername;

    String username;
    String passwordAktualni;
    String passwordNove;
    String passwordNoveOvereni;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //nacte do promenne username username zvoleneho ucitele ze trıdy PrihlaseniController
        username = PrihlaseniController.ucitelUsernamePrihlaseni;

        //overi zda ucitel opravdu zvolil nejakeho ucitele, bez toho by program heslo zmenit nedokazal
        if (username!=null) {
            label_notUsername.setText(PrihlaseniController.UcitelPoVybrani);
        }else {
            label_notUsername.setText("Není vybraný učitel.\n" +
                    "Pro vybrání učitele se vraťte\n " +
                    "na přihlašovací stránku\n" +
                    " pomocí tlačítka \"Přihlásit se\" ");
        }

        //do promenne passwordAktualni nacte heslo pro vybraneho ucitele
        try{
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT password FROM ucitele\n" +
                    "WHERE username = \""+username+"\"");

            while(resultSet.next()) {
                passwordAktualni = resultSet.getString("password");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    //tlacitko pro zmenu hesla
        button_Zmenit.setOnAction(actionEvent -> {

            String aktualniHeslo = String.valueOf(tf_stareHeslo.getText());
            passwordNove = String.valueOf(tf_noveHeslo.getText());
            passwordNoveOvereni = String.valueOf(tf_noveHesloOvereni.getText());

            //prvni podminka zkontroluje zda aktualni heslo je spravne
            //druha zkontroluje jestli se obe napsana nova hesla shoduji - kdyby se uzivatel prepsal
            //pri dodrzeni obou podminek program aktualizuje stare heslo na heslo nove
                if (aktualniHeslo.equals(passwordAktualni)) {
                    if (passwordNove.equals(passwordNoveOvereni)) {
                        try {
                            ConnectionClass connectionClass = new ConnectionClass();
                            Connection connection = connectionClass.getConnection();
                            Statement statement = connection.createStatement();
                            statement.execute("UPDATE ucitele SET password = \"" + passwordNove + "\"\n" +
                                    "WHERE username = \"" + username + "\"");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        label_uspech.setText("Heslo bylo úspěšně změněno");
                    } else {
                        label_chybaStareHeslo.setText("");
                        label_chybaNoveHeslo.setText("Hesla se neshodují");
                        label_uspech.setText("");
                    }
                } else {
                    label_chybaNoveHeslo.setText("");
                    label_uspech.setText("");
                    label_chybaStareHeslo.setText("Nesprávné heslo");
                }

        });

    //tlacitko prihlasit se - vrati ucitele na prihlasovaci scenu a nastvani username na null
        button_prihlasitSe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PrihlaseniController.ucitelUsernamePrihlaseni = null;
                DBUtils.changeScene(actionEvent, "prihlaseni.fxml","Přihlášení");
            }
        });
    }
}
