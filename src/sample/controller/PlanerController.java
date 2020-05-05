package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.databaseConnection.Connect;
import sample.databaseConnection.PatientQueries;
import sample.model.Patient;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlanerController implements Initializable {
    @FXML ChoiceBox patient;
    @FXML ChoiceBox fromTime;
    @FXML ChoiceBox toTime;
    @FXML TextArea description;
    @FXML Label patientNameLabel;
    @FXML Label patientSSNLabel;
    public String ssnQ;
    public static ArrayList<Patient> pa = null;
    public static Patient patientHere;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> time = getBoxValues();
        for (String choiceBoxTimes : time){
            fromTime.getItems().addAll(choiceBoxTimes);
            toTime.getItems().addAll(choiceBoxTimes);
        }

        try {
            PatientQueries psql = new PatientQueries();
            psql.viewPatientTable();
             pa = psql.getPatientsinfo();
            for (Patient patients : pa){
                patient.getItems().addAll(patients.getFirstName()+" "+patients.getLastName());

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        patient.setOnAction(e ->{
            String a = (String) patient.getValue();
            String[] b = a.split(" ");
            for (Patient patientss : pa) {
                if (patientss.getFirstName().equals(b[0]) && patientss.getLastName().equals(b[1])){
                    ssnQ = patientss.getSSN();
                    patientSSNLabel.setText(patientss.getSSN());
                    patientNameLabel.setText(patientss.getFirstName() +" "+patientss.getLastName());
                }

            }
        });

    }
    public void labelSetter() {

    }

    public void apply() throws SQLException {
        Connection connect = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
        PreparedStatement statement = connect.prepareStatement("INSERT INTO schedule (patient_name, time_from, time_to, description) VALUES(?,?,?,?);");
        statement.setString(1, (String) patient.getValue());
        statement.setString(2, (String) fromTime.getValue());
        statement.setString(3, (String) toTime.getValue());
        statement.setString(4, description.getText());
        statement.executeUpdate();
        System.out.println("Worked!");
    }




public ArrayList<String> getBoxValues() {
    ArrayList<String> time = new ArrayList<>();
        time.add("8:00"); time.add("8:30"); time.add("9:00");
        time.add("9:30"); time.add("10:00"); time.add("10:30");
        time.add("11:00"); time.add("11:30"); time.add("12:00");
        time.add("12:30"); time.add("13:00"); time.add("13:30");
        time.add("14:00"); time.add("14:30"); time.add("15:00");
        time.add("15:30"); time.add("16:00");
    return time;
}



}

