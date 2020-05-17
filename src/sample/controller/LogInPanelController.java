package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.databaseConnection.StaffQueries;

import javax.tools.Tool;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LogInPanelController implements Initializable {
    @FXML
    private CheckBox showPass;
    @FXML
    private PasswordField passWord;
    @FXML
    private TextField userName;
    @FXML
    private Button forgotPassButton;

    private SwitchScene sc = new SwitchScene();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Tooltip tooltipUsername = new Tooltip();
        tooltipUsername.setText("Enter your username if you are admin, nurse or planer");
        userName.setTooltip(tooltipUsername);

        final Tooltip tooltipPassword = new Tooltip();
        tooltipPassword.setText("Enter your password to access all functions for your role");
        passWord.setTooltip(tooltipPassword);

        final Tooltip tooltipShowPass = new Tooltip();
        tooltipShowPass.setText("check this box if you wish to see your password");
        showPass.setTooltip(tooltipShowPass);

        final Tooltip tooltipForgotPassword = new Tooltip();
        tooltipForgotPassword.setText("Press this button if you forgot your password and wish to reset it");
        forgotPassButton.setTooltip(tooltipForgotPassword);

    }


    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/LogIn.fxml");
    }

    public void logIn(ActionEvent actionEvent) throws IOException, SQLException {//i will add later some sql statements once the database is done
        StaffQueries login = new StaffQueries();
        int row = login.verifyStaffLogin(userName.getText(), passWord.getText());


        if (row == 1 && userName.getText().endsWith("@yahoo.com")) {
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/admin.fxml");

        } else if (row == 1 && userName.getText().endsWith("@gmail.com")) {
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/nurse.fxml");

        } else if (row == 1 && userName.getText().endsWith("@hotmail.com")) {
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/planer.fxml");

        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("Sorry, logging in was not possible please make sure your account information is correct!"+"\n"+"Try again...");
        }
    }

    public void showPass(ActionEvent actionEvent) {
        if (showPass.isSelected()) {
            passWord.setPromptText(passWord.getText());
            passWord.setText("");
            passWord.setDisable(true);

        }else {
            passWord .setText(passWord.getPromptText());
            passWord.setPromptText("Password");
            passWord.setDisable(false);
        }
    }

    public void forgetPassword(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/forgotPassword.fxml");
    }


}