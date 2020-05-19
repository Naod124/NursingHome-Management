package sample.controller;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.StaffQueries;
import sample.model.AlertMaker;
import sample.model.StaffTable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ManageEmployeesController implements Initializable {
    @FXML
    private Button removeButton;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private TextField searchIdField;
    @FXML
    private TextField searchLastNameField;
    @FXML
    private TableView<StaffTable> employeesTable;
    @FXML
    private TableColumn<StaffTable, String> Name;
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

    private StaffQueries staffQueries = new StaffQueries();

    private AlertMaker alertMaker = new AlertMaker();

    private static StaffTable selectedItem = new StaffTable();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Tooltip tooltipEmplyoeeTxtField = new Tooltip();
        tooltipEmplyoeeTxtField.setText("Enter the emplyoees last name that you want to find ");
        searchLastNameField.setTooltip(tooltipEmplyoeeTxtField);

        final Tooltip tooltipAllCheckBox = new Tooltip();
        tooltipAllCheckBox.setText("Check this box to view all emplyoees");
        all.setTooltip(tooltipAllCheckBox);

        final Tooltip tooltipNurse = new Tooltip();
        tooltipNurse.setText("Check this box to view only the nurses");
        nurses.setTooltip(tooltipNurse);

        final Tooltip tooltipPlaner = new Tooltip();
        tooltipPlaner.setText("Check this box to view the planers");
        planers.setTooltip(tooltipPlaner);

        final Tooltip tooltipTable = new Tooltip();
        tooltipTable.setText("Press right click on the row you want to update");
        employeesTable.setTooltip(tooltipTable);


        final Tooltip tooltipRemoveButton = new Tooltip();
        tooltipRemoveButton.setText("Press this button to remove the selected row");
        removeButton.setTooltip(tooltipRemoveButton);

        employeesTable.setEditable(true);
        try {
            viewStaff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        searchFunctionById();
        searchFunctionByName();

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

    public void searchFunctionByName() {
        FilteredList<StaffTable> filteredList = new FilteredList<StaffTable>(staffQueries.getObList(), b -> true);
        searchLastNameField.setOnKeyReleased(e -> {
            searchLastNameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
                filteredList.setPredicate((Predicate<? super StaffTable>) s -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (s.getName().contains(newValue)) {
                        return true;
                    } else if (s.getName().toLowerCase().contains(lowerCaseFilter)) {
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

    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/admin.fxml");

    }

    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void registerNewEmployee(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/addNewEmployee.fxml");

    }

    public void removeEmployee(ActionEvent actionEvent) throws IOException {
        deleteEmployeeFromTable();
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
            a.setContentText("Sorry, removing patient could not go through" + "\n" + "Try again...");
            a.showAndWait();
        }
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

    public void setEmployeesTable() {
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        role.setCellValueFactory(new PropertyValueFactory<>("Role"));
    }


    public void updateEmployeeInfo() throws IOException {
        if (selectedItem != null) {
            selectedItem = employeesTable.getSelectionModel().getSelectedItem();
            System.out.println(employeesTable.getSelectionModel().getSelectedItem().getName());
            sc.newStage("/sample/view/UpdateEmployeeInfo.fxml");
        } else {
            alertMaker.simpleAlert(" Please select an employee ", " No Employee selected  ");

        }
    }

    public static StaffTable getSelectedItem() {
        return selectedItem;
    }
    //    public StaffTable wlashe() {
//        selectedPerson = employeesTable.getSelectionModel().getSelectedItem();
//
//            System.out.println(selectedPerson.getFirstName());
//            return selectedPerson;
//        } else {
//            alertMaker.simpleAlert(" Please select an employee ", " No Employee selected  ");
//            return null;
//        }
//    }
}
