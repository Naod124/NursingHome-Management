package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class AdminController {
    @FXML
    private Label welcomeLabel;

    private SwitchScene sc = new SwitchScene();


    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "LogIn.fxml");
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
