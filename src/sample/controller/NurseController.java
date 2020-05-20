package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import sample.databaseConnection.PatientQueries;

import java.io.IOException;
import java.sql.SQLException;

public class NurseController {

    // keep track of nurse username , password and SSN
    public static String username;
    public static String password;

    // creating object to access patient query class method
    PatientQueries pq = new PatientQueries();

    SwitchScene sc = new SwitchScene();

    @FXML
    public void addPatientScene(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/addPatient.fxml");
    }

    @FXML
    public void removeScene(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/RemovePatient.fxml");
    }

    @FXML
    public void schedule(ActionEvent ae) throws IOException {
        sc.newScene(ae,"../view/displaySchedule.fxml" );
    }

    @FXML
    public void editPatientScene(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/editPatient.fxml");
    }

    @FXML
    public void back(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/LogIn.fxml");
    }
    @FXML
    public void switchToCreateSchedule(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/schema.fxml");
    }

    @FXML
    public void exit(ActionEvent ae) throws IOException {
        System.exit(0);

    }

    @FXML
    void assign(ActionEvent ae) throws IOException, SQLException {
        sc.newScene(ae, "../view/AssignPatient.fxml");
        // Getting SSN of nurse to keep track of nurse
        int nurseSSN = pq.getNurseSSN(username, password);
        // assigning SSN of nurse
        AssignPatientController.nurseSSN = nurseSSN;
    }

    @FXML
    void diagnoses(ActionEvent ae) throws IOException, SQLException {
        sc.newScene(ae, "../view/diagnosePatient.fxml");
        // Getting SSN of nurse to keep track of nurse
        int nurseSSN = pq.getNurseSSN(username, password);
        // assigning SSN of nurse
        DiagnosePatientController.nurseSSN = nurseSSN;
    }

    @FXML
    void visit(ActionEvent ae) throws IOException, SQLException {
        sc.newScene(ae, "../view/VisitPatient.fxml");
        // Getting SSN of nurse to keep track of nurse
        int nurseSSN = pq.getNurseSSN(username, password);
        // assigning SSN of nurse
        VisitPatientController.nurseSSN = nurseSSN;
    }

    @FXML
    void getassignpatient(ActionEvent event) throws IOException {
        sc.newScene(event, "../view/GetAssignPatient.fxml");
    }
}
