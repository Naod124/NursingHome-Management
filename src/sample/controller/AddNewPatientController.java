package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class AddNewPatientController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private TextField ssn;
    @FXML
    private TextField age;
    @FXML
    private TextField userName;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    private ArrayList<Patient> patients = new ArrayList<>();

    public void add(ActionEvent actionEvent) {

        Patient patient = new Patient(firstName.getText(), lastName.getText(), address.getText(), ssn.getText(), Integer.parseInt(age.getText()), email.getText());
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("patient.ser"))) {
            patients.add(patient);
            objectOutputStream.writeObject(patients);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(readPatient());

    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/sample/view/LogIn.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public ArrayList<Patient> readPatient() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("patient.ser"))) {
            return (ArrayList<Patient>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void help(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) {
    }
}
