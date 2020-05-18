package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {

    @FXML
    private TextArea txtarea;

    SwitchScene sc = new SwitchScene();




    @FXML public void backtoLogIn(ActionEvent ae) throws IOException {

        sc.newScene(ae, "../view/LogIn.fxml");
    }

    @FXML public void exitApp(){
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        txtarea.setEditable(false);

        txtarea.setText("This application is for the Admin, nurses and planer that works in this nursing \nhome. " +
                "\n  If you are the Adminstrator then log in with your admin login information \n and you can therefore" +
                " have the access to view all patients,view all employees,\n add emplyoees and remove " +
                "employees that no longer are working here. \n \n If you are a Nurse then log in with your nurse login" +
                " \n information and you can therefore have the options to to" +
                " view all patients, \nremove patients, edit patient info, get assigned patients and check their\n diagnosis. \n\n" +
                "If you are the planer then log in with your admin login information and you can\n therefore " +
                "create the schedule for the nurses. ");
    }
}
