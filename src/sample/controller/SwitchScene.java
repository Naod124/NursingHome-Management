package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScene {

    public void newScene(ActionEvent event, String fxmlFileName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void newStage(String fxmlFileName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFileName));
        Scene scene = new Scene(root);
        Stage window = new Stage();
        window.setScene(scene);
        window.show();
    }
}