package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import sample.databaseConnection.StaffQueries;
import sample.model.AlertMaker;
import sample.model.StaffTable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateEmployeeInfo implements Initializable {
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
    private ChoiceBox<String> role;
    private StaffQueries staffQueries = new StaffQueries();
    private AlertMaker alertMaker = new AlertMaker();
    private FXMLLoader loader = new FXMLLoader();
    private SwitchScene sc = new SwitchScene();

    public void update(ActionEvent actionEvent) {
        try {
            staffQueries.updateIntoStaffTable(firstName.getText(), lastName.getText(), dOb.getText(), ssn.getText(), email.getText(), address.getText(), salary.getText());
            String name = firstName.getText() + " " + lastName.getText();
            alertMaker.infoAlert( name + " information has been successfully updated in data base " , "Successfully");
        } catch (SQLException e) {
           alertMaker.errorAlert("Sorry, updating this employer information was not possible!" + "\n" + "Try again...","Error!");
           System.out.println(e.getMessage());
        }
    }


    public void cancel(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/admin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getSelectedPerson();
    }

    public void getSelectedPerson() {
        loader.setLocation(getClass().getResource("/sample/view/manageEmployees.fxml"));
        try {
            loader.load();
            StaffTable staffTable = ManageEmployeesController.getSelectedItem();
            firstName.setText(staffTable.getName());
            lastName.setText(staffTable.getName());
            ssn.setText(staffTable.getSsn());
            email.setText(staffTable.getEmail());
            address.setText(staffTable.getAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
