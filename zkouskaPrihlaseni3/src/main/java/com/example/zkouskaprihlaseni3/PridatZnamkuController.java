package com.example.zkouskaprihlaseni3;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PridatZnamkuController implements Initializable {
    @FXML
    private ComboBox cmbox_student;
    @FXML
    private ComboBox<Integer> cmbox_znamka;
    @FXML
    private ComboBox<Integer> cmbox_hodnota;
    @FXML
    private TextField tf_test;


    @FXML
    private Button button_pridat;

    private ArrayList<Integer> znamky = new ArrayList<>();
    private ArrayList<Integer> hodnoty = new ArrayList<>();
    ArrayList<StudentJmeno> studenti = new ArrayList<>();


    String student;
    String test;
    int znamka;
    int hodnota;
    String krestni;
    String prijmeni;
    private ZnamkovaniController mycontroller;

    public void setParentController(ZnamkovaniController controller) {
        this.mycontroller = controller;
    }
    public Stage getStage(Stage stage){
        return stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //diky temto info vi jaky id_trida_predmet_ucitel pouzit
        String trida = ZnamkovaniController.tridaComboBox;
        String predmet = ZnamkovaniController.predmetComboBox;
        String username = ZnamkovaniController.usernameUcitel;


            //nacte jmena studentu zvolene tridy a vlozi je do comboboxu
            try{
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT krestniJmeno, prijmeni FROM studenti s \n" +
                        "JOIN tridy t ON(s.trida_id=t.trida_id)\n" +
                        "WHERE trida_jmeno = \""+trida+"\"");
                while(resultSet.next()) {
                    String krestni = resultSet.getString("krestniJmeno");
                    String prijmeni = resultSet.getString("prijmeni");
                    studenti.add(new StudentJmeno(krestni, prijmeni));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
             cmbox_student.getItems().addAll(studenti);

        //diky tomuto se v comboboxu znamky zobrazi moznost znamek 1-5 a v comboboxu hodnoty 1-10
        for (int i = 1; i < 6; i++) {
            znamky.add(i);
        }
        for (int i = 1; i < 11; i++) {
            hodnoty.add(i);
        }

        cmbox_znamka.getItems().addAll(znamky);
        cmbox_hodnota.getItems().addAll(hodnoty);

        //dostaneme ke komu to chce pridat

        cmbox_student.setOnAction(actionEvent->{
            student = String.valueOf(cmbox_student.getValue());
            String[] parts = student.split(" ");
            krestni = parts[0];
            prijmeni = parts[1];
        });
        cmbox_znamka.setOnAction(actionEvent->{
            znamka = cmbox_znamka.getValue();
        });
        cmbox_hodnota.setOnAction(actionEvent->{
            hodnota = cmbox_hodnota.getValue();
        });


        button_pridat.setOnAction(actionEvent->{
            //nacte text z textfieldu pro jmeno testu
            test = tf_test.getText();
                    try{

                        //prida do databaze novou znamku
                        ConnectionClass connectionClass = new ConnectionClass();
                        Connection connection = connectionClass.getConnection();
                        Statement statement = connection.createStatement();
                       statement.execute("INSERT INTO zaznamyznamek (student_id,id_trida_predmet_ucitel,test,znamka,hodnota)\n" +
                               "SELECT s.student_id,tpu.id_trida_predmet_ucitel,\""+test+"\","+znamka+","+hodnota+"\n" +
                               "FROM studenti s\n" +
                               "JOIN trida_predmet_ucitel tpu\n" +
                               "JOIN predmety p ON (tpu.predmet_id=p.predmet_id)\n" +
                               "JOIN tridy t ON (tpu.trida_id=t.trida_id)\n" +
                               "JOIN ucitele u ON (tpu.ucitel_id=u.ucitel_id)\n" +
                               "WHERE s.krestniJmeno = \""+krestni+"\" AND s.prijmeni = \""+prijmeni+"\" \n" +
                               "AND p.predmet_nazev = \""+predmet+"\" \n" +
                               "AND t.trida_jmeno = \""+trida+"\" \n" +
                               "AND u.username = \""+username+"\" ");

                    }catch (Exception e){
                        e.printStackTrace();
                    }
            //pomoci toho se okno zavre
                    mycontroller.getStage().close();
        });
    }
}

