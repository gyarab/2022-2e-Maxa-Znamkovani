package com.example.zkouskaprihlaseni3;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class OdebratZnamkuController implements Initializable {


    @FXML
    private Button button_odebrat;
    @FXML
    private Button button_ponechat;
    @FXML
    private Label label_notZnamka;

    private ZnamkovaniController mycontroller;

    public void setParentController(ZnamkovaniController controller) {
        this.mycontroller = controller;
    }
    public Stage getStage(Stage stage){
        return stage;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    //tlacitko odebrat znamku
        button_odebrat.setOnAction(actionEvent->{
            //jestli ucitel nevybral zadny zaznam z tabulky, program mu to napise
           label_notZnamka.setText("Není vybraná známka");
           //vrati id zvoleneho zaznamu v tabulce
           StudentZnamkaZaznam selectedItems = (StudentZnamkaZaznam) mycontroller.getTable().getSelectionModel().getSelectedItems().get(0);
           int znamkaID = selectedItems.id;

           //odstrani zvoleny zaznam z databaze
               try {
                   ConnectionClass connectionClass = new ConnectionClass();
                   Connection connection = connectionClass.getConnection();
                   Statement statement = connection.createStatement();
                   statement.execute("delete from zaznamyznamek where zaznamZnamky_id=" + znamkaID);

               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }
               mycontroller.getStage().close();
        } );

        button_ponechat.setOnAction(actionEvent->{
            mycontroller.getStage().close();
        });


    }
}