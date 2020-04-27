package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInPanelController {
    @FXML private TextField passWord;
    @FXML private TextField userName;
    private SwitchScene sc = new SwitchScene();

    public void back(ActionEvent actionEvent) throws IOException {
      sc.newScene(actionEvent,"view/LogIn.fxml");
    }

    public void logIn(ActionEvent actionEvent) throws IOException { //i will add later some sql statements once the database is done
        if (userName.getText().equals("Nurse@email.com") && (passWord.getText().equals("1234"))) {
            String link = "/sample/view/nurse.fxml";
            SwitchScene sc = new SwitchScene();
            sc.newScene(actionEvent, link);
        }else{
            System.out.println("Error");
        }

    }
}
