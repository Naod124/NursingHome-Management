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
        sc.newScene(actionEvent, "/sample/view/LogIn.fxml");
    }


    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addNewStaff(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/addNewEmployee.fxml");
    }

    public void removeStaff(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/removeEmployee.fxml");
    }

    public void viewPatients(ActionEvent actionEvent) {
    }

    public void help(ActionEvent actionEvent) {
    }
}
