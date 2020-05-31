package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sample.databaseConnection.PatientQueries;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NurseController implements Initializable {

    // keep track of nurse username , password and SSN
    public static String username;
    public static String password;
    public Label welcomeLabel;

    // creating object to access patient query class method
    PatientQueries pq = new PatientQueries();

    SwitchScene sc = new SwitchScene();

    @FXML
    public void schedule(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/displaySchedule.fxml");
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
    public void exit(ActionEvent ae) throws IOException {
        System.exit(0);

    }

    @FXML
    void assign(ActionEvent ae) throws IOException, SQLException {
        sc.newScene(ae, "../view/AssignPatient.fxml");
        // Getting SSN of nurse to keep track of nurse
        String nurseSSN = pq.getNurseSSN(username, password);
        // assigning SSN of nurse
        AssignPatientController.nurseSSN = nurseSSN;
    }

    @FXML
    void diagnoses(ActionEvent ae) throws IOException, SQLException {
        sc.newScene(ae, "../view/diagnosePatient.fxml");
        // Getting SSN of nurse to keep track of nurse
        String nurseSSN = pq.getNurseSSN(username, password);
        // assigning SSN of nurse
        DiagnosePatientController.nurseSSN = nurseSSN;
    }

    @FXML
    void visit(ActionEvent ae) throws IOException, SQLException {
        sc.newScene(ae, "../view/VisitPatient.fxml");
        // Getting SSN of nurse to keep track of nurse
        String nurseSSN = pq.getNurseSSN(username, password);
        // assigning SSN of nurse
        VisitPatientController.nurseSSN = nurseSSN;
    }

    @FXML
    void getassignpatient(ActionEvent event) throws IOException {
        sc.newScene(event, "../view/GetAssignPatient.fxml");
    }

    public void viewPatients(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/viewPatientNurse.fxml");
    }

    public void switchToAssign(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/AssignPatient.fxml");
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
