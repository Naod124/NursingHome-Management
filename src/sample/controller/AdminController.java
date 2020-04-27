package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    @FXML
    private Label welcomeLabel;

    private SwitchScene sc = new SwitchScene();


    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/LogIn.fxml");
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addNewStuff(ActionEvent actionEvent) {
    }

    public void removeStaff(ActionEvent actionEvent) {
    }

    public void viewPatients(ActionEvent actionEvent) {
    }

    public void help(ActionEvent actionEvent) {
    }
}
