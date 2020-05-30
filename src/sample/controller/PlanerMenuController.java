package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PlanerMenuController {
    SwitchScene sc = new SwitchScene();

    @FXML public void createSchedule(ActionEvent ae) throws IOException {
        sc.newScene(ae, "/sample/view/schema.fxml");

    }
    @FXML public void signOut(ActionEvent ae) throws IOException {
        sc.newScene(ae, "/sample/view/LogIn.fxml");

    }

     public void switchToViewAssignedPatients(ActionEvent ae) throws IOException {
        sc.newScene(ae, "/sample/view/GetAssignPatient.fxml");
    }

    public void switchToAssign(ActionEvent ae) throws IOException {
        sc.newScene(ae, "/sample/view/AssignPatient.fxml");
    }
}
