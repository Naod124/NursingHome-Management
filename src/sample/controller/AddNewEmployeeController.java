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
    private static ArrayList<Staff> staff = new ArrayList<>();

    public void add(ActionEvent actionEvent) throws FileNotFoundException {
        String userNameO = userName.getText();
        String password = passWord.getText();
        String firstNameO = firstName.getText();
        String lastNameO = lastName.getText();
        String addressO = address.getText();
        String SSN = ssn.getText();
        String ageO = age.getText();
        String emailO = email.getText();
        if (chooseEmployeeRole.getValue().equalsIgnoreCase("Doctor")) {
            LogIn logIn = new LogIn(userNameO, password);
            Doctor doctor = new Doctor(firstNameO, lastNameO, addressO, SSN, Integer.parseInt(ageO), emailO, logIn, 20000);
            staff.add(doctor);
            System.out.println(doctor);
        }
        if (chooseEmployeeRole.getValue().equalsIgnoreCase("Nurse")) {
            LogIn logIn = new LogIn(userNameO, password);
            Nurse nurse = new Nurse(firstNameO, lastNameO, addressO, SSN, Integer.parseInt(ageO), emailO, logIn, 20000);
            staff.add(nurse);
            System.out.println(nurse);

        }
        if (chooseEmployeeRole.getValue().equalsIgnoreCase("Planer")) {
            LogIn logIn = new LogIn(userNameO, password);
            Planer planer = new Planer(firstNameO, lastNameO, addressO, SSN, Integer.parseInt(ageO), emailO, logIn, 20000);
            staff.add(planer);
            System.out.println(planer);

        }
        if (chooseEmployeeRole.getValue().equalsIgnoreCase("Assistant")) {
            LogIn logIn = new LogIn(userNameO, password);
            Doctor doctor = new Doctor(firstNameO, lastNameO, addressO, SSN, Integer.parseInt(ageO), emailO, logIn, 20000);
            staff.add(doctor);
            System.out.println(doctor);
        }
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("employees.ser"))){
            objectOutputStream.writeObject(staff);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/sample/view/admin.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void help(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/sample/view/LogIn.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void chooseRole(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        chooseEmployeeRole.getItems().addAll("Doctor", "Nurse", "Planer", "Assistant");

    }
}
