package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.cj.log.Log;
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
    public static String nurseSSN;
    VisitTable selectedItem = new VisitTable();
    ObservableList<VisitTable> data = FXCollections.observableArrayList();
    ArrayList<String> timetable = new ArrayList<>();

    @FXML
    void add(ActionEvent event) throws SQLException {
        String text = visittextfield.getText();
        if (!(text == null)) {
            selectedItem.setVisitor(visittextfield.getText());
            selectedItem.setVisitortime(freetime.getValue());
            String updateFreeTime = updateFreeTime();
            vq.insertIntoTable(visittextfield.getText(), ssntextfield.getText(), freetime.getValue(), updateFreeTime);
            selectedItem.setFreeTime(updateFreeTime);
            alertMaker.infoAlert("Visitor Added Successfully", "Done!");
            for (int i = 0; i < data.size(); i++) {
                String ssn = data.get(i).getSsn();
                if (ssn.equals(selectedItem.getSsn())) {
                    data.set(i, selectedItem);
                    refresh();
                }
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Some Fields are Empty").showAndWait();
        }
    }

    @FXML
    void remove(ActionEvent event) throws SQLException {
        selectedItem.setVisitor("");
        selectedItem.setVisitortime("");
        vq.deleteIntoTable(ssntextfield.getText());
        alertMaker.infoAlert("Visitor Removed Successfully", "Done!");
        for (int i = 0; i < data.size(); i++) {
            String ssn = data.get(i).getSsn();
            if (ssn.equals(selectedItem.getSsn())) {
                data.set(i, selectedItem);
                refresh();
            }
        }
    }

    @FXML
    void update(ActionEvent event) throws SQLException {
        selectedItem.setVisitor(visittextfield.getText());
        selectedItem.setVisitortime(freetime.getValue());
        String updateFreeTime = updateFreeTime();
        vq.UpdateIntoTable(visittextfield.getText(), ssntextfield.getText(), freetime.getValue(), updateFreeTime);
        selectedItem.setFreeTime(updateFreeTime);
        alertMaker.infoAlert("Diagonosis Updated Successfully", "Done!");
        for (int i = 0; i < data.size(); i++) {
            String ssn = data.get(i).getSsn();
            if (ssn.equals(selectedItem.getSsn())) {
                data.set(i, selectedItem);
                refresh();
            }
        }

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        if (LogInPanelController.role.equals("nurse")) {
            sc.newScene(event, "/sample/view/nurse.fxml");
        }else {
            sc.newScene(event, "/sample/view/planer.fxml");
        }
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void fectchdata(MouseEvent event) {
        selectedItem = (VisitTable) table.getSelectionModel().getSelectedItem();
        ssntextfield.setText(selectedItem.getSsn());
        firstnametextfield.setText(selectedItem.getFirstName());
        lastnametextfield.setText(selectedItem.getLastName());
        gendertextfield.setText(selectedItem.getGender());
        datetextfield.setText(selectedItem.getDateOfBirth());
        visittextfield.setText(selectedItem.getVisitor());
        visitortime.setText(selectedItem.getVisitortime());

        String[] split = selectedItem.getFreeTime().split(",");
        timetable.clear();
        if (split.length != 0) {
            for (int i = 0; i < split.length; i++) {
                timetable.add(split[i]);
            }
            freetime.getItems().clear();
            freetime.getItems().addAll(timetable);
        } else {
            freetime.getItems().clear();
            freetime.getItems().addAll("No Time Available");
        }


//		freetime.setValue(selectedItem.get);

    }

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

        ssntextfield.setEditable(false);
        firstnametextfield.setEditable(false);
        lastnametextfield.setEditable(false);
        datetextfield.setEditable(false);
        gendertextfield.setEditable(false);


        ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        freetimecol.setCellValueFactory(new PropertyValueFactory<>("FreeTime"));
        visitorcol.setCellValueFactory(new PropertyValueFactory<>("Visitor"));

        try {
            ArrayList<VisitTable> view = vq.viewTable();
            data.addAll(view);
            table.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void refresh() {
        table.setItems(data);
    }

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
