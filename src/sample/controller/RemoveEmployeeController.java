package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    private ObservableList<StaffTable> obList = FXCollections.observableArrayList();

    private ComboBox<String> staffToView;

    private sample.databaseConnection.Staff DB = new sample.databaseConnection.Staff();

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
        sc.newScene(actionEvent,"/sample/view/admin.fxml");
    }

    public void backToMain(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent,"/sample/view/logIn.fxml");
    }

    public void help(ActionEvent actionEvent) {
    }

    public void viewStaff() throws SQLException {
        ResultSet rs;
        String selectQuery = "SELECT * FROM STAFF;";

        conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        rs = conn.createStatement().executeQuery(selectQuery);

        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        role.setCellValueFactory(new PropertyValueFactory<>("Role"));

        while (rs.next()) {
            StaffTable pt = new StaffTable("FirstName", "LastName", "SSN", "E-mail", "Address", "Role");

            pt.setFirstName(rs.getString("FirstName"));
            pt.setLastName(rs.getString("LastName"));
            pt.setSsn(rs.getString("ssn"));
            pt.setEmail(rs.getString("Email"));
            pt.setAddress(rs.getString("Adress"));
            pt.setRole(rs.getString("Role"));

            obList.add(pt);

        }

        employeesTable.setItems(obList);

    }

    public void deleteEmployeeFromTable() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        StaffTable employeeToremove = employeesTable.getSelectionModel().getSelectedItem();
        String deleteQuery = "DELETE FROM staff WHERE SSN = ?";
        pstmt = conn.prepareStatement(deleteQuery);
        pstmt.setString(1, employeeToremove.getSsn());
        pstmt.executeUpdate();
        employeesTable.getItems().clear();
        viewStaff();
        System.out.println("Database updated !");
    }
}

