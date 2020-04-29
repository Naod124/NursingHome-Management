package sample.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.model.*;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewEmployeeController implements Initializable {
    @FXML
    private ChoiceBox<String> chooseRole;
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
        String firstNameO = firstName.getText();
        String lastNameO    = lastName.getText();
        String addressO = address.getText();
        String SSN = ssn.getText();
        int ageO = Integer.parseInt(age.getText());
        String Email = email.getText();
        String username = userName.getText();
        String password = passWord.getText();
        String role = chooseRole.getValue();
        switch (role){
            case "Nurse":
                LogIn logIn = new LogIn(username,password);
                Nurse nurse = new Nurse(firstNameO,lastNameO,addressO,SSN,ageO,Email,logIn,20000);
            case "Planer":
                LogIn logIn1 = new LogIn(username,password);
                Nurse planer = new Nurse(firstNameO,lastNameO,addressO,SSN,ageO,Email,logIn1,20000);
            case "Assistant":
                LogIn logIn2 = new LogIn(username,password);
                Nurse assistant = new Nurse(firstNameO,lastNameO,addressO,SSN,ageO,Email,logIn2,20000);
        }

    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent , "/sample/view/admin.fxml");
    }

    public void help(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent , "/sample/view/logIn.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseRole.getItems().addAll("Nurse","Planer","Assistant");
    }
}
