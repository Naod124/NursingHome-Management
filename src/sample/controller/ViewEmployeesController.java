package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.StaffQueries;
import sample.model.StaffTable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewEmployeesController implements Initializable {
    @FXML
    private TableView<StaffTable> employeesTable;
    @FXML
    private TableColumn<StaffTable, String> firstName;
    @FXML
    private TableColumn<StaffTable, String> lastName;
    @FXML
    private TableColumn<StaffTable, String> ssn;
    @FXML
    private TableColumn<StaffTable, String> email;
    @FXML
    private TableColumn<StaffTable, String> address;
    @FXML
    private TableColumn<StaffTable, String> role;
    @FXML
    private CheckBox all;
    @FXML
    private CheckBox nurses;
    @FXML
    private CheckBox planers;

    private SwitchScene sc = new SwitchScene();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeesTable.setEditable(true);
        try {
            if (all.isSelected()) {
                viewStaff();
            } else if (nurses.isSelected()) {
                viewNurse();
            } else if (planers.isSelected()) {
                viewPlaner();
            } else viewStaff();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void viewStaff() throws SQLException {
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        role.setCellValueFactory(new PropertyValueFactory<>("Role"));
        StaffQueries sq = new StaffQueries();
        sq.viewAllStaffTable();
        employeesTable.setItems(sq.getObList());
    }

    public void viewNurse() throws SQLException {
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        role.setCellValueFactory(new PropertyValueFactory<>("Role"));
        StaffQueries sq = new StaffQueries();
        sq.viewNurseTable();
        employeesTable.setItems(sq.getObList());
    }

    public void viewPlaner() throws SQLException {
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        role.setCellValueFactory(new PropertyValueFactory<>("Role"));
        StaffQueries sq = new StaffQueries();
        sq.viewPlanerTable();
        employeesTable.setItems(sq.getObList());
    }

    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/admin.fxml");

    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void registerNewEmployee(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/addNewEmployee.fxml");

    }

    public void unRegisterEmployee(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/removeEmployee.fxml");

    }

    public void showAll(ActionEvent actionEvent) throws SQLException {
        if (all.isSelected()) {
            viewStaff();
            nurses.setDisable(true);
            planers.setDisable(true);
        } else {
            nurses.setDisable(false);
            planers.setDisable(false);
        }
    }

    public void showNurses(ActionEvent actionEvent) throws SQLException {
        if (nurses.isSelected()) {
            viewNurse();
            all.setDisable(true);
            planers.setDisable(true);
        } else {
            all.setDisable(false);
            planers.setDisable(false);
        }

    }

    public void showPlaners(ActionEvent actionEvent) throws SQLException {
        if (planers.isSelected()) {
            viewPlaner();
            nurses.setDisable(true);
            all.setDisable(true);
        } else {
            nurses.setDisable(false);
            all.setDisable(false);
        }
    }
}
