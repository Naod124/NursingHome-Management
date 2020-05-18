package sample.controller;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import java.util.function.Predicate;

public class RemoveEmployeeController implements Initializable {
    @FXML
    private TextField searchLastNameField;
    @FXML
    private TextField searchIdField;
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

    @FXML
    private Button removeButton;

    private Connection conn;
    private PreparedStatement pstmt;
    private SwitchScene sc = new SwitchScene();


    private StaffQueries staffQueries = new StaffQueries();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Tooltip tooltipAllCheckBox = new Tooltip();
        tooltipAllCheckBox.setText("Check this box to view all emplyoees");
        all.setTooltip(tooltipAllCheckBox);

        final Tooltip tooltipNurse = new Tooltip();
        tooltipNurse.setText("Check this box to view only the nurses");
        nurses.setTooltip(tooltipNurse);

        final Tooltip tooltipPlaner = new Tooltip();
        tooltipPlaner.setText("Check this box to view only the planers ");
        planers.setTooltip(tooltipPlaner);


        final Tooltip tooltipTable = new Tooltip();
        tooltipTable.setText("Press on the row you want to delete");
        employeesTable.setTooltip(tooltipTable);


        final Tooltip tooltipRemoveButton = new Tooltip();
        tooltipRemoveButton.setText("Press this button to remove the selected row");
        removeButton.setTooltip(tooltipRemoveButton);


        employeesTable.setEditable(true);
        try {
            viewStaff();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        searchFunctionById();
        searchFunctionByLastName();
    }

    public void searchFunctionById() {
        FilteredList<StaffTable> filteredList = new FilteredList<StaffTable>(staffQueries.getObList(), b -> true);
        searchIdField.setOnKeyReleased(e -> {
            searchIdField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super StaffTable>) s -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (s.getSsn().contains(newValue)) {
                        return true;
                    } else if (s.getSsn().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<StaffTable> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(employeesTable.comparatorProperty());
            employeesTable.setItems(sortedList);
        });
    }

    public void searchFunctionByLastName() {
        FilteredList<StaffTable> filteredList = new FilteredList<StaffTable>(staffQueries.getObList(), b -> true);
        searchLastNameField.setOnKeyReleased(e -> {
            searchLastNameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super StaffTable>) s -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (s.getLastName().contains(newValue)) {
                        return true;
                    } else if (s.getLastName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            }));
            SortedList<StaffTable> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(employeesTable.comparatorProperty());
            employeesTable.setItems(sortedList);
        });
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
            employeesTable.getItems().clear();
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
            employeesTable.getItems().clear();
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
            employeesTable.getItems().clear();
            viewPlaner();
            nurses.setDisable(true);
            all.setDisable(true);
        } else {
            nurses.setDisable(false);
            all.setDisable(false);
        }
    }

    public void deleteEmployeeFromTable() {
        try {

            StaffTable st = employeesTable.getSelectionModel().getSelectedItem();
            staffQueries.removeStaff(st.getSsn());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Staff member removed");
            a.showAndWait();
            employeesTable.getItems().clear();
            viewStaff();
        } catch (SQLException e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("Sorry, removing patient could not go through"+"\n"+"Try again...");
            a.showAndWait();
        }
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