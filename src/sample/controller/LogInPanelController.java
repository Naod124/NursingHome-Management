package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import sample.databaseConnection.Connect;
import sample.databaseConnection.Staff;

import java.io.IOException;
import java.sql.*;

public class LogInPanelController {
    @FXML private TextField passWord;
    @FXML private TextField userName;
    private SwitchScene sc = new SwitchScene();

    public void back(ActionEvent actionEvent) throws IOException {
      sc.newScene(actionEvent,"/sample/view/LogIn.fxml");
    }

    public void logIn(ActionEvent actionEvent) throws IOException, SQLException {//i will add later some sql statements once the database is done
        Staff login = new Staff();
        int row = login.verifyStaffLogin(userName.getText(), passWord.getText());
        System.out.println(row);

        if (row == 1 && userName.getText().endsWith("@admin.com")) {
                SwitchScene sc = new SwitchScene();
                sc.newScene(actionEvent, "/sample/view/admin.fxml");

            }else if (row == 1 && userName.getText().endsWith("@nurse.com")){
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/nurse.fxml");

        }else if (row == 1 && userName.getText().endsWith("@planer.com")){
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, "/sample/view/planer.fxml");

        }else{
            System.out.println("Error");
        }
    }
}
