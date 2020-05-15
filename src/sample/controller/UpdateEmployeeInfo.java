package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.databaseConnection.StaffQueries;

import java.sql.SQLException;

public class UpdateEmployeeInfo {
    @FXML
    private TextField salary;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private TextField ssn;
    @FXML
    private TextField dOb;
    @FXML
    private TextField email;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;
    @FXML
    private ChoiceBox<String> role;

    private StaffQueries staffQueries = new StaffQueries();

    public void update(ActionEvent actionEvent) throws SQLException {
        staffQueries.updateIntoStaffTable(firstName.getText(), lastName.getText(), dOb.getText(), ssn.getText(), email.getText(), role.getValue(), address.getText(), salary.getText());
    }

    public void help(ActionEvent actionEvent) {
    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) {
    }
}
