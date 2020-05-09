package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.databaseConnection.StaffQueries;

import java.io.IOException;
import java.sql.*;

public class LogInPanelController {
    @FXML
    private CheckBox showPass;
    @FXML
    private PasswordField passWord;
    @FXML
    private TextField userName;
    private SwitchScene sc = new SwitchScene();

    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/LogIn.fxml");
    }

    public void logIn(ActionEvent actionEvent) throws IOException, SQLException {//i will add later some sql statements once the database is done
        StaffQueries login = new StaffQueries();
        int row = login.verifyStaffLogin(userName.getText(), passWord.getText());


        if (row == 1 && userName.getText().endsWith("@admin.com")) {
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/admin.fxml");

        } else if (row == 1 && userName.getText().endsWith("@nurse.com")) {
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/nurse.fxml");

        } else if (row == 1 && userName.getText().endsWith("@planer.com")) {
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/planer.fxml");

        } else {
            System.out.println("Error");
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

    public void forgetPassword(ActionEvent actionEvent) {
    }
}
