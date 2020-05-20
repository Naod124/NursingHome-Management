package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.databaseConnection.VisitQueries;
import sample.model.AlertMaker;
import sample.model.VisitTable;

public class VisitPatientController implements Initializable {

    @FXML
    private TableView table;

    @FXML
    private TableColumn<VisitTable, String> ssncol;

    @FXML
    private TableColumn<VisitTable, String> firstnamecol;

    @FXML
    private TableColumn<VisitTable, String> lastnamecol;

    @FXML
    private TableColumn<VisitTable, String> dobcol;

    @FXML
    private TableColumn<VisitTable, String> gendercol;

    @FXML
    private TableColumn<VisitTable, String> freetimecol;

    @FXML
    private TableColumn<VisitTable, String> visitorcol;

    @FXML
    private TextField ssntextfield;

    @FXML
    private TextField firstnametextfield;

    @FXML
    private TextField lastnametextfield;

    @FXML
    private TextField gendertextfield;

    @FXML
    private TextField datetextfield;

    @FXML
    private ComboBox<String> freetime;

    @FXML
    private TextField visitortime;

    @FXML
    private TextField visittextfield;
    @FXML
    private Button removeButton;

    @FXML
    private Button UpdateButton;


    @FXML
    private Button addButton;

    private SwitchScene sc = new SwitchScene();
    private AlertMaker alertMaker = new AlertMaker();

    VisitQueries vq = new VisitQueries();
    // to keep track of nurse SSN
    public static int nurseSSN;
    // for keeping record of selected data
    VisitTable selectedItem = new VisitTable();
    // for storing data fetch from database
    ObservableList<VisitTable> data = FXCollections.observableArrayList();
    // keeping record of patient free time
    ArrayList<String> timetable = new ArrayList<>();

    @FXML
    void add(ActionEvent event) throws SQLException {
        String text = visittextfield.getText();
        // checking if the field is empty before inserting diagnose
        if (!(text == null)) {
            // setting the visitor and visitor time of the selected item
            selectedItem.setVisitor(visittextfield.getText());
            selectedItem.setVisitortime(freetime.getValue());
            // get the remaining free time of the patient
            String updateFreeTime = updateFreeTime();
            // inserting data into the database and update free time
            vq.insertIntoTable(visittextfield.getText(), ssntextfield.getText(), freetime.getValue(), updateFreeTime);
            // setting the updated time of the patient
            selectedItem.setFreeTime(updateFreeTime);
            // showing alert
            alertMaker.infoAlert("Visitor Added Successfully", "Done!");
            for (int i = 0; i < data.size(); i++) {
                // getting the SSN of selected table data to change field of diagnose
                String ssn = data.get(i).getSsn();
                if (ssn.equals(selectedItem.getSsn())) {
                    // updating the diagnose field present in table data
                    data.set(i, selectedItem);
                    // refreshing table
                    refresh();
                }
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Some Fields are Empty").showAndWait();
        }
    }

    @FXML
    void remove(ActionEvent event) throws SQLException {
        // updating the diagnose field of selected data to null because we are going to
        // remove it from database
        selectedItem.setVisitor("");
        selectedItem.setVisitortime("");
        // query for removing the the data in database
        vq.deleteIntoTable(ssntextfield.getText());
        // showing alert
        alertMaker.infoAlert("Visitor Removed Successfully", "Done!");
        for (int i = 0; i < data.size(); i++) {
            // getting the SSN of selected table data to change field of diagnose
            String ssn = data.get(i).getSsn();
            if (ssn.equals(selectedItem.getSsn())) {
                // updating the diagnose field present in table data
                data.set(i, selectedItem);
                // refreshing table
                refresh();
            }
        }
    }

    @FXML
    void update(ActionEvent event) throws SQLException {
        // setting the visitor and visitor time of the selected item
        selectedItem.setVisitor(visittextfield.getText());
        selectedItem.setVisitortime(freetime.getValue());
        // get the remaining free time of the patient
        String updateFreeTime = updateFreeTime();
        // inserting data into the database and update free time
        vq.UpdateIntoTable(visittextfield.getText(), ssntextfield.getText(), freetime.getValue(), updateFreeTime);
        // setting the updated time of the patient
        selectedItem.setFreeTime(updateFreeTime);
        // showing alert
        alertMaker.infoAlert("Diagonosis Updated Successfully", "Done!");
        for (int i = 0; i < data.size(); i++) {
            // getting the SSN of selected table data to change field of diagnose
            String ssn = data.get(i).getSsn();
            if (ssn.equals(selectedItem.getSsn())) {
                // updating the diagnose field present in table data
                data.set(i, selectedItem);
                // refreshing table
                refresh();
            }
        }

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        sc.newScene(event, "/sample/view/nurse.fxml");
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void fectchdata(MouseEvent event) {
        // getting the selected data from table while clicking on the row
        selectedItem = (VisitTable) table.getSelectionModel().getSelectedItem();
        // setting the fields with information that is selected
        ssntextfield.setText(selectedItem.getSsn());
        firstnametextfield.setText(selectedItem.getFirstName());
        lastnametextfield.setText(selectedItem.getLastName());
        gendertextfield.setText(selectedItem.getGender());
        datetextfield.setText(selectedItem.getDateOfBirth());
        visittextfield.setText(selectedItem.getVisitor());
        visitortime.setText(selectedItem.getVisitortime());

        // getting the free time of patient from database and spliting with respect to
        // comma
        String[] split = selectedItem.getFreeTime().split(",");
        // clearing the array
        timetable.clear();
        // now adding splited data into the timetable list
        if (split.length != 0) {
            for (int i = 0; i < split.length; i++) {
                timetable.add(split[i]);
            }
            // clearing the combo-box item
            freetime.getItems().clear();
            // adding the time available to the combo-box
            freetime.getItems().addAll(timetable);
        } else {
            freetime.getItems().clear();
            // setting no time Available if empty
            freetime.getItems().addAll("No Time Available");
        }


//		freetime.setValue(selectedItem.get);

    }

    // method for initializing table and getting data from database
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        final Tooltip tooltipSsn = new Tooltip();
        tooltipSsn.setText("Enter the patients social security number. It shall be in this format: yymmdd****");
        ssntextfield.setTooltip(tooltipSsn);


        final Tooltip tooltipFirstname = new Tooltip();
        tooltipFirstname.setText("Enter the firstname of the patient");
        firstnametextfield.setTooltip(tooltipFirstname);


        final Tooltip tooltipLastname = new Tooltip();
        tooltipLastname.setText("Enter the lastname of the patient");
        lastnametextfield.setTooltip(tooltipLastname);


        final Tooltip tooltipdate = new Tooltip();
        tooltipdate.setText("Enter date of birth of the patient in this format: YYYY-MM-DD");
        datetextfield.setTooltip(tooltipdate);

        final Tooltip tooltipGender = new Tooltip();
        tooltipGender.setText("Enter sex of the patient. It shall be Male or Female");
        gendertextfield.setTooltip(tooltipGender);

        final Tooltip tooltipVisitTime = new Tooltip();
        tooltipVisitTime.setText("Enter the time for the visit");
        visitortime.setTooltip(tooltipVisitTime);

        final Tooltip tooltipVisit = new Tooltip();
        tooltipVisit.setText("Enter the kind of visit");
        visittextfield.setTooltip(tooltipVisit);

        final Tooltip tooltipChooseTime = new Tooltip();
        tooltipChooseTime.setText("choose a time");
        freetime.setTooltip(tooltipChooseTime);

        final Tooltip tooltipAddButton = new Tooltip();
        tooltipAddButton.setText("Press this button to add the visit");
        addButton.setTooltip(tooltipAddButton);

        final Tooltip tooltipUpdateButton = new Tooltip();
        tooltipUpdateButton.setText("Press this button to update the visit");
        UpdateButton.setTooltip(tooltipUpdateButton);

        final Tooltip tooltipRemoveButton = new Tooltip();
        tooltipRemoveButton.setText("Press this button to remove the visit");
        removeButton.setTooltip(tooltipRemoveButton);


        // setting the table columns
        ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        freetimecol.setCellValueFactory(new PropertyValueFactory<>("FreeTime"));
        visitorcol.setCellValueFactory(new PropertyValueFactory<>("Visitor"));

        try {
            // getting data from database
            ArrayList<VisitTable> view = vq.viewTable();
            data.addAll(view);
            // setting data to table
            table.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // method to refresh change in data after different operation
    public void refresh() {
        table.setItems(data);
    }

    // updating free time of the patient
    public String updateFreeTime() {
        String remainingfreetime = "";
        timetable.remove(freetime.getValue());
        for (int i = 0; i < timetable.size(); i++) {
            remainingfreetime += timetable.get(i) + ",";
        }
        return remainingfreetime;
    }

    @FXML
    void setvisitortime(ActionEvent event) {
        visitortime.setText(freetime.getValue());
    }
}
