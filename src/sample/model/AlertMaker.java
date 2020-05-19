package sample.model;

import javafx.scene.control.Alert;

public class AlertMaker {
    public void simpleAlert(String messageToDisplay , String alertTitle ) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(messageToDisplay);
        alert.setHeaderText(alertTitle);
        alert.show();
    }

    public void confirmAlert(String messageToDisplay , String alertTitle) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(messageToDisplay);
        alert.setHeaderText(alertTitle);
        alert.show();
    }

    public void errorAlert(String messageToDisplay , String alertTitle) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(messageToDisplay);
        alert.setHeaderText(alertTitle);
        alert.show();
    }

    public void infoAlert(String messageToDisplay , String alertTitle) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(messageToDisplay);
        alert.setHeaderText(alertTitle);
        alert.show();
    }
}

