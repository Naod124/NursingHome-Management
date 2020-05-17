package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private Button signIn;

    @FXML
    private Button contactUs;

    @FXML
    private Button exitButton;



    private SwitchScene sc = new SwitchScene();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Tooltip tooltipSignIn = new Tooltip();
        tooltipSignIn.setText("Press this button to sign in");
        signIn.setTooltip(tooltipSignIn);


        final Tooltip tooltipcontactUs = new Tooltip();
        tooltipcontactUs.setText("press this button to contact us");
        contactUs.setTooltip(tooltipcontactUs);


        final Tooltip tooltipexit = new Tooltip();
        tooltipexit.setText("Press this button to exit the application");
        exitButton.setTooltip(tooltipexit);


    }

    @FXML
    public void signIn(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/LogInPanel.fxml");
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void Help(ActionEvent actionEvent) throws IOException {

        sc.newScene(actionEvent,"/sample/view/Help.fxml");
    }


}
