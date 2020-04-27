package sample.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.*;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewEmployeeController implements Initializable {
    @FXML
    private JFXComboBox<String> employeeRole;
    @FXML
    private ChoiceBox<String> chooseEmployeeRole;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;
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
    private TextField email;

    private SwitchScene sc = new SwitchScene();

    private static ArrayList<Staff> staff = new ArrayList<>();

    public void add(ActionEvent actionEvent) throws FileNotFoundException {

    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent , "admin.fxml");
    }

    public void help(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent , "logIn.fxml");
    }

    public void chooseRole(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
