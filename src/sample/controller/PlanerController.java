package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.databaseConnection.PatientQueries;
import sample.databaseConnection.StaffQueries;
import sample.model.AlertMaker;
import sample.model.Patient;
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
            fromTime.getItems().addAll(String.valueOf(choiceBoxTimes));
            toTime.getItems().addAll(String.valueOf(choiceBoxTimes));
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
        try {
            StaffQueries sq = new StaffQueries();
            String fromT = (String) fromTime.getValue();
            int a = Integer.parseInt(fromT);
            String toT = (String) toTime.getValue();
            int b = Integer.parseInt(toT);

            if (patient.getValue() == null || fromTime.getValue() == null || toTime.getValue() == null
                    || description.getText().isEmpty() || a < b)
            {
                alertMaker.errorAlert("You have not added all fields", "Error");
            }
            else {
                sq.planerApply(patient.getValue(), toTime.getValue(), fromTime.getValue(),description.getText());
                alertMaker.confirmAlert("INSERTED!", "Successfully!");
            }

        } catch (Exception e) {
            alertMaker.errorAlert("Sorry, We could not apply your information!" + "\n" + "Try again...","Error!");
        }
    }

    @FXML
    public void backToNurse(ActionEvent event) throws IOException {
        sc.newScene(event, "../view/planer.fxml");
    }


    public ArrayList<String> getBoxValues() {
        ArrayList<String> time = new ArrayList<>();
        time.add("8");
        time.add("9");
        time.add("10");
        time.add("11");
        time.add("12");
        time.add("13");
        time.add("14");
        time.add("15");
        time.add("16");

        return time;
    }



}

