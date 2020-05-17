package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class PlanerMenuController {
    SwitchScene sc = new SwitchScene();

    @FXML public void createSchedule(ActionEvent ae) throws IOException {
        sc.newScene(ae, "../view/schema.fxml");

    }
}
