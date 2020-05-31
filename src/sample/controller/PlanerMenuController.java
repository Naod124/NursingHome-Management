package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlanerMenuController implements Initializable {
    public Label welcomeLabel;
    SwitchScene sc = new SwitchScene();

    @FXML
    public void createSchedule(ActionEvent ae) throws IOException {
        sc.newScene(ae, "/sample/view/schema.fxml");

    }

    @FXML
    public void signOut(ActionEvent ae) throws IOException {
        sc.newScene(ae, "/sample/view/LogIn.fxml");

    }

    public void switchToAssign(ActionEvent ae) throws IOException {
        sc.newScene(ae, "/sample/view/AssignPatient.fxml");
    }

    public void viewPatients(ActionEvent actionEvent) {
    }

    public void addPatientScene(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "../view/addPatient.fxml");
    }

    public void removeScene(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "../view/RemovePatient.fxml");
    }

    public void schedule(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "../view/displaySchedule.fxml");
    }

    public void visit(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "../view/VisitPatient.fxml");
    }

    public void setName() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/logInPanel.fxml"));
            loader.load();
            LogInPanelController log = loader.getController();
            String name = log.getEmployeeName();
            welcomeLabel.setText("Welcome " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setVisible(true);
        setName();
    }
}
