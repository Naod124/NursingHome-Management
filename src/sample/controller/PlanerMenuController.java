package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PlanerMenuController {
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
}
