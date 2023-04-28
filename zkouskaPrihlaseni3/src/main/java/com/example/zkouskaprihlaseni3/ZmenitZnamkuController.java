package com.example.zkouskaprihlaseni3;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ZmenitZnamkuController implements Initializable {
    @FXML
    private Button button_zmenitZnamku;
    @FXML
    private TextField tf_test;
    @FXML
    private ComboBox<Integer> cmbox_znamka;
    @FXML
    private ComboBox<Integer> cmbox_hodnota;
    @FXML
    private Label label_student;

    private ArrayList<Integer> znamky = new ArrayList<>();
    private ArrayList<Integer> hodnoty = new ArrayList<>();

    private ZnamkovaniController mycontroller;

    public void setParentController(ZnamkovaniController controller) {
        this.mycontroller = controller;
    }
    public Stage getStage(Stage stage){
        return stage;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //diky tomuto se v comboboxu znamky zobrazi moznost znamek 1-5 a v comboboxu hodnoty 1-10
        for (int i = 1; i < 6; i++) {
            znamky.add(i);
        }
        for (int i = 1; i < 11; i++) {
            hodnoty.add(i);
        }
        //Pro prehlednost program vytvorı pomocne promenne:
        String krestni = ZnamkovaniController.krestnizmenitZnamku;
        String prijmeni = ZnamkovaniController.prijmenizmenitZnamku;
        String test = ZnamkovaniController.testzmenitZnamku;
        int znamka = ZnamkovaniController.znamkazmenitZnamku;
        int hodnota = ZnamkovaniController.hodnotazmenitZnamku;
        int znamkaID = ZnamkovaniController.znamkaIDzmenitZnamku;


        label_student.setText(krestni+" "+prijmeni);
        cmbox_znamka.setValue(znamka);
        cmbox_hodnota.setValue(hodnota);
        tf_test.setText(test);
        cmbox_znamka.getItems().addAll(znamky);
        cmbox_hodnota.getItems().addAll(hodnoty);

    //nastaveni tlacitka Upravit znamku
        button_zmenitZnamku.setOnAction(actionEvent->{
            //vysledna hodnota, znamka a jmeno testu
             int znamkaV = cmbox_znamka.getValue();
             int hodnotaV = cmbox_hodnota.getValue();
             String testV = String.valueOf(tf_test.getText());

             //aktualizuje vybrany zaznam znamky v databazi
             try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                Statement statement = connection.createStatement();
                statement.execute("update zaznamyznamek set test=\""+testV+"\", znamka= "+ znamkaV +", hodnota = "+hodnotaV+" WHERE zaznamZnamky_id=" + znamkaID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
             //pomoci toho se okno zavre
                mycontroller.getStage().close();
        });
    }
}
