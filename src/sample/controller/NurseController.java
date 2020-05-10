package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

import java.io.IOException;

public class NurseController {


    SwitchScene sc = new SwitchScene();



    @FXML
    public void managepatientscene(ActionEvent ae) throws IOException {

        sc.newScene(ae,"../view/ManagePatient.fxml");

    }

    @FXML
    public void removescene(ActionEvent ae) throws IOException {

        sc.newScene(ae,"../view/RemovePatient.fxml");

}
    @FXML
    public void back(ActionEvent ae) throws IOException {

        sc.newScene(ae, "../view/LogIn.fxml");

    }

    @FXML
    public void exit(ActionEvent ae) throws IOException {
        System.exit(0);

    }





}


