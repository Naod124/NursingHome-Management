package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInController {

    @FXML
    private Button admin;
    @FXML
    private Button planer;
    @FXML
    private Button nurse;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;

    private SwitchScene sc = new SwitchScene();

    @FXML
    public void nurseScene(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "view/LogInPanel.fxml");

    }

    @FXML
    public void planerScene(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "view/LogInPanel.fxml");
    }

    @FXML
    public void adminScene(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "view/LogInPanel.fxml");
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void help(ActionEvent actionEvent) throws IOException {

    }

}
