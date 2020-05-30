package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.databaseConnection.Connect;
import sample.databaseConnection.PatientQueries;
import sample.databaseConnection.StaffQueries;
import sample.model.AlertMaker;
import sample.model.Patient;
import sample.model.Staff;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlanerController implements Initializable {

    @FXML
    private ChoiceBox patient;
    @FXML
    private ChoiceBox fromTime;
    @FXML
    private ChoiceBox toTime;
    @FXML
    private TextArea description;
    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientSSNLabel;


    public String ssnQ;
    public static ArrayList<Patient> pa = null;
    public static Patient patientHere;
    private SwitchScene sc = new SwitchScene();
    private AlertMaker alertMaker = new AlertMaker();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Tooltip tooltipPatients = new Tooltip();
        tooltipPatients.setText("Choose the patient");
        patient.setTooltip(tooltipPatients);

        final Tooltip tooltipFromTime = new Tooltip();
        tooltipFromTime.setText("choose a t time from");
        fromTime.setTooltip(tooltipFromTime);


        final Tooltip tooltiptoTime = new Tooltip();
        tooltipFromTime.setText("choose a t time to");
        fromTime.setTooltip(tooltiptoTime);


        ArrayList<String> time = getBoxValues();
        for (String choiceBoxTimes : time) {
            fromTime.getItems().addAll(choiceBoxTimes);
            toTime.getItems().addAll(choiceBoxTimes);
        }

        patient.setOnAction(e -> {
            String a = (String) patient.getValue();
            String[] b = a.split(" ");
            for (Patient patientss : pa) {
                if (patientss.getFirstName().equals(b[0]) && patientss.getLastName().equals(b[1])) {
                    ssnQ = patientss.getSSN();
                    patientSSNLabel.setText(patientss.getSSN());
                    patientNameLabel.setText(patientss.getFirstName() + " " + patientss.getLastName());
                }

            }
        });

        labelSetter();

    }

    public void labelSetter() {
        try {
            PatientQueries psql = new PatientQueries();
            psql.viewPatientTable();
            pa = psql.getPatientsinfo();
            for (Patient patients : pa) {
                patient.getItems().addAll(patients.getFirstName() + " " + patients.getLastName());


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void apply() throws SQLException {
       /* Connection connect = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
        PreparedStatement statement = connect.prepareStatement("INSERT INTO schedule (patient_name, time_from, time_to, description) VALUES(?,?,?,?);");
        statement.setString(1, (String) patient.getValue());
        statement.setString(2, (String) fromTime.getValue());
        statement.setString(3, (String) toTime.getValue());
        statement.setString(4, description.getText());
        statement.executeUpdate();
        System.out.println("Worked!");
    */
        try {
            StaffQueries sq = new StaffQueries();
            if (patient.getValue() == null || fromTime.getValue() == null || toTime.getValue() == null
                    || description.getText().isEmpty())
            {
                alertMaker.errorAlert("You have not added all fields", "Error");
            }
            else {
                sq.planerApply(patient.getValue(), fromTime.getValue(), toTime.getValue(), description.getText());
                alertMaker.confirmAlert("INSERTED!", "Successfully!");
            }
        } catch (Exception e) {
            alertMaker.errorAlert("Sorry, We could not apply your information!" + "\n" + "Try again...","Error!");
        }
    }

    @FXML
    public void backToNurse(ActionEvent event) throws IOException {
        sc.newScene(event, "../view/nurse.fxml");
    }


    public ArrayList<String> getBoxValues() {
        ArrayList<String> time = new ArrayList<>();
        time.add("08:00");
        time.add("08:30");
        time.add("09:00");
        time.add("09:30");
        time.add("10:00");
        time.add("10:30");
        time.add("11:00");
        time.add("11:30");
        time.add("12:00");
        time.add("12:30");
        time.add("13:00");
        time.add("13:30");
        time.add("14:00");
        time.add("14:30");
        time.add("15:00");
        time.add("15:30");
        time.add("16:00");
        return time;
    }




}

