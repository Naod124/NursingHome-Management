package sample.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.StaffQueries;
import sample.model.StaffTable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RemoveEmployeeController implements Initializable {
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
    @FXML
    private TableView<StaffTable> employeesTable;

    private Connection conn;
    private PreparedStatement pstmt;
    private SwitchScene sc = new SwitchScene();


    private StaffQueries staffQueries = new StaffQueries();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        employeesTable.setEditable(true);
        try {
            viewStaff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeEmployee(ActionEvent actionEvent) throws SQLException {
        deleteEmployeeFromTable();
    }

    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/admin.fxml");
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/logIn.fxml");
    }

    public void help(ActionEvent actionEvent) {
    }

    public void viewStaff() throws SQLException {
        setEmployeesTable();
        staffQueries.viewAllStaffTable();
        employeesTable.setItems(staffQueries.getObList());

    }

    public void viewNurse() throws SQLException {
        setEmployeesTable();
        staffQueries.viewNurseTable();
        employeesTable.setItems(staffQueries.getObList());
    }

    public void viewPlaner() throws SQLException {
        setEmployeesTable();
        staffQueries.viewPlanerTable();
        employeesTable.setItems(staffQueries.getObList());
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

    public void deleteEmployeeFromTable() throws SQLException {
        StaffTable st = employeesTable.getSelectionModel().getSelectedItem();
        staffQueries.removeStaff(st.getSsn());
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("Staff member removed");
        a.showAndWait();
        employeesTable.getItems().clear();
        viewStaff();
    }

    public void setEmployeesTable() {
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        role.setCellValueFactory(new PropertyValueFactory<>("Role"));
    }
}

