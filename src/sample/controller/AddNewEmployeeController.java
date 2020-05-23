package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.databaseConnection.StaffQueries;
import sample.model.AlertMaker;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddNewEmployeeController implements Initializable {
    @FXML
    private Label addressLabel;
    @FXML
    private Label dobLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label ssnLabel;
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
    private AlertMaker alertMaker = new AlertMaker();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName.textProperty().addListener(e -> {
            if (firstName.getText().matches("^[a-zA-Z]+$") && firstName.getText() != null) {
                firstNameLabel.setVisible(true);
                firstNameLabel.setText("Good");
                firstName.setStyle("-fx-text-inner-color: #0db9de");
            } else if (firstName.getText().isEmpty()) {
                firstNameLabel.setVisible(false);
            } else {
                firstNameLabel.setVisible(true);
                firstNameLabel.setText("Bad");
                firstName.setStyle("-fx-text-inner-color: red");
                firstNameLabel.setStyle("-fx-text-inner-color: red");
            }
        });
        lastName.textProperty().addListener(e -> {
            if (lastName.getText().matches("^[a-zA-Z]+$")) {
                lastNameLabel.setVisible(true);
                lastNameLabel.setText("Good");
                lastName.setStyle("-fx-text-inner-color: #0db9de");
            } else if (lastName.getText().isEmpty()) {
                lastNameLabel.setVisible(false);
            } else {
                lastNameLabel.setVisible(true);
                lastNameLabel.setText("Bad");
                lastName.setStyle("-fx-text-inner-color: red");
                lastNameLabel.setStyle("-fx-text-inner-color: red");
            }
        });
        email.textProperty().addListener(e -> {
            if (email.getText().contains("@") && email.getText().contains(".")) {
                emailLabel.setVisible(true);
                emailLabel.setText("Good");
                email.setStyle("-fx-text-inner-color: #0db9de");
            } else if (email.getText().isEmpty()) {
                emailLabel.setVisible(false);
            } else {
                emailLabel.setVisible(true);
                emailLabel.setText("Bad");
                email.setStyle("-fx-text-inner-color: red");
            }
        });
        ssn.textProperty().addListener(e -> {
            if (ssn.getText().matches("^[0-9]{10}+$")) {
                ssnLabel.setVisible(true);
                ssnLabel.setText("Good");
                ssn.setStyle("-fx-text-inner-color: #0db9de");
            } else if (ssn.getText().isEmpty()) {
                ssnLabel.setVisible(false);
            } else {
                ssnLabel.setVisible(true);
                ssnLabel.setText("Bad");
                ssn.setStyle("-fx-text-inner-color: red");
                ssnLabel.setStyle("-fx-text-inner-color: red");
            }
        });
        dOb.textProperty().addListener(e -> {
            if (dOb.getText().matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                dobLabel.setVisible(true);
                dobLabel.setText("Good");
                dOb.setStyle("-fx-text-inner-color: #0db9de");
            } else if (dOb.getText().isEmpty()) {
                dobLabel.setVisible(false);
            } else {
                dobLabel.setVisible(true);
                dobLabel.setText("Bad");
                dOb.setStyle("-fx-text-inner-color: red");
                dobLabel.setStyle("-fx-text-inner-color: red");
            }
        });
        passWord.textProperty().addListener(e -> {
            if (passWord.getText().matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
                passwordLabel.setVisible(true);
                passwordLabel.setText("Good");
                passWord.setStyle("-fx-text-inner-color: #0db9de");
            } else if (passWord.getText().isEmpty()) {
                passwordLabel.setVisible(false);
            } else {
                passwordLabel.setVisible(true);
                passwordLabel.setText("Bad");
                passWord.setStyle("-fx-text-inner-color: red");
                passwordLabel.setStyle("-fx-text-inner-color: red");
            }
        });
        address.textProperty().addListener(e -> {
            if (address.getText().matches("^[#.0-9a-zA-Z-ÖöÄäÅå\\s,-]+$")) {
                addressLabel.setVisible(true);
                addressLabel.setText("Good");
                address.setStyle("-fx-text-inner-color: #0db9de");
            } else if (address.getText().isEmpty()) {
                addressLabel.setVisible(false);
            } else {
                addressLabel.setVisible(true);
                addressLabel.setText("Bad");
                address.setStyle("-fx-text-inner-color: red");
                addressLabel.setStyle("-fx-text-inner-color: red");
            }
        });
        userName.textProperty().addListener(e -> {
            if (userName.getText().contains("@") && email.getText().contains(".")) {
                userNameLabel.setVisible(true);
                userNameLabel.setText("Good");
                userName.setStyle("-fx-text-inner-color: #0db9de");
            } else if (userName.getText().isEmpty()) {
                userNameLabel.setVisible(false);
            } else {
                userNameLabel.setVisible(true);
                userNameLabel.setText("Bad");
                userName.setStyle("-fx-text-inner-color: red");
                userNameLabel.setStyle("-fx-text-inner-color: red");
            }
        });

        role.getItems().addAll("Nurse", "Planer");

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
            if (firstNameO.matches("^[a-zA-Z]+$") && lastNameO.matches("^[a-zA-Z]+$") && SSN.matches("^[0-9]{12}$") && addressO.matches("^[#.0-9a-zA-Z-ÖöÄäÅå\\s,-]+$") && dob.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")
                    && password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$") && username.contains("@") && email.getText().contains(".") && Email.contains("@") && email.getText().contains(".")) {

                logIn.insertIntoPStaffTable(firstNameO, lastNameO, SSN, addressO, dob, Email, 20000, roleO, username, password);
                System.out.println("Done ! Check DataBase");
                alertMaker.infoAlert(firstNameO + "" + lastNameO + " has been successfully added to the dataBase as " + roleO, "Successfully");
            } else
                alertMaker.simpleAlert("Some fields are incorrect or already exist , please check the red text .. ", "Invalid Input");
//                                        alertMaker.simpleAlert("password must contain at least eight characters, at least one number and both lower and uppercase letters and special characters", "Invalid Input");
//                                    alertMaker.simpleAlert("Please check your username it's maybe aleardy exist or has wrong format", "Invalid Input");
//                                alertMaker.simpleAlert("Please Enter a valid Email address", "Invalid input");
//                            alertMaker.simpleAlert("Please Cheack your Birthday field so it has this format (YYYY-MM-DD)", "Input mismatch");
//                        alertMaker.simpleAlert("Please check your address fields", "Input mismatch");
//                    alertMaker.simpleAlert("Please check your social security number so it should contain 10 digits", "Input mismatch");
//                alertMaker.simpleAlert("Please check your name fields", "Input mismatch");
        } catch (Exception e) {
            alertMaker.errorAlert("Adding a staff member did not go through!" + "\n" + "Please try again...", "Error!");
        }
    }

    public void cancel(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/admin.fxml");
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/logIn.fxml");
    }


    public void done(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/manageEmployees.fxml");
    }
}
