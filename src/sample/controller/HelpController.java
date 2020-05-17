package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HelpController {

    SwitchScene sc = new SwitchScene();
    @FXML public void backtoLogIn(ActionEvent ae) throws IOException {

        sc.newScene(ae, "../view/LogIn.fxml");
    }

    @FXML public void exitApp(){
        System.exit(0);
    }
}
