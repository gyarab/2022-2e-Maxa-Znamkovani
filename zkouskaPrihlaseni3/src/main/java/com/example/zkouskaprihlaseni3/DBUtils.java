package com.example.zkouskaprihlaseni3;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class DBUtils{
    private static Stage stage;
    private static Scene scene;
    private static Parent root;
    public static void changeScene(ActionEvent event, String fxmlFile, String title) {
        //metoda ktera zmeni scenu - zobrazi jiny FXML soubor
        Parent root = null;
        try {
          FXMLLoader fxmlLoader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
          root = fxmlLoader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }


}
