package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LogInController {


    private SwitchScene sc = new SwitchScene();

    @FXML
    public void signIn(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/LogInPanel.fxml");
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void contactUs(ActionEvent actionEvent) throws IOException {

    }
}
