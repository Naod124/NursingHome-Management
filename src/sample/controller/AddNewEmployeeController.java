package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.databaseConnection.StaffQueries;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewEmployeeController implements Initializable {
    @FXML
    private TextField dOb;
    @FXML
    private ChoiceBox<String> role;
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
    private TextField email;

    @FXML
    private Button addButton;

    private SwitchScene sc = new SwitchScene();

    public void add(ActionEvent actionEvent) throws FileNotFoundException {
        try {
            String firstNameO = firstName.getText();
            String lastNameO = lastName.getText();
            String addressO = address.getText();
            String SSN = ssn.getText();
            String dob = dOb.getText();
            String Email = email.getText();
            String username = userName.getText();
            String password = passWord.getText();
            String roleO = role.getValue();
            StaffQueries logIn = new StaffQueries();
            logIn.insertIntoPStaffTable(firstNameO, lastNameO, SSN, addressO, dob, Email, 20000, roleO, username, password);
            System.out.println("Done ! Check DataBase");
        } catch (Exception e) {
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("Adding a staff member did not go through!" + "\n" + "Please try again...");
        }
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/admin.fxml");
    }

    public void help(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/logIn.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        role.getItems().addAll("Nurse", "Planer", "Assistant");

        final Tooltip tooltipSsn = new Tooltip();
        tooltipSsn.setText("Enter the social security number of the emplyoee. It shall be in this format: yymmdd****");
        ssn.setTooltip(tooltipSsn);

        final Tooltip tooltipFirstName = new Tooltip();
        tooltipFirstName.setText("Enter the first name");
        firstName.setTooltip(tooltipFirstName);


        final Tooltip tooltipLastName = new Tooltip();
        tooltipLastName.setText("Enter the last name");
        lastName.setTooltip(tooltipLastName);


        final Tooltip tooltipDob = new Tooltip();
        tooltipDob.setText("Enter your birthday in this format: YYYY-MM-DD");
        dOb.setTooltip(tooltipDob);


        final Tooltip tooltipPlaceBorn = new Tooltip();
        tooltipPlaceBorn.setText("Enter the place of residence");
        address.setTooltip(tooltipPlaceBorn);


        final Tooltip tooltipEmail = new Tooltip();
        tooltipEmail.setText("Enter your email adress");
        email.setTooltip(tooltipEmail);


        final Tooltip tooltipUsername = new Tooltip();
        tooltipUsername.setText("Type in your username. Username is the same as email");
        userName.setTooltip(tooltipUsername);


        final Tooltip tooltipPassword = new Tooltip();
        tooltipPassword.setText("Enter the password. It can contain any character");
        passWord.setTooltip(tooltipPassword);


        final Tooltip tooltipRole = new Tooltip();
        tooltipRole.setText("Select the role for this emplyoee");
        role.setTooltip(tooltipRole);


        final Tooltip tooltipAddButton = new Tooltip();
        tooltipAddButton.setText("Press this button when you have entered all information to add this employee");
        addButton.setTooltip(tooltipAddButton);

    }
}
