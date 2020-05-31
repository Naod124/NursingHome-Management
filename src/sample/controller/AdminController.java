package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    private Label welcomeLabel;
    private SwitchScene sc = new SwitchScene();
    private String name;

    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/LogIn.fxml");
    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void addNewStaff(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/addNewEmployee.fxml");
    }

    public void viewPatients(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/viewPatients.fxml");
    }

    public void viewAllEmployees(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/manageEmployees.fxml");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setVisible(true);
        setName();

    }

    public void setName() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/logInPanel.fxml"));
            loader.load();
            LogInPanelController log = loader.getController();
            name = log.getEmployeeName();
            welcomeLabel.setText("Welcome " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
