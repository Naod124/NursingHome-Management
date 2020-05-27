package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.databaseConnection.DiagnosQueries;
import sample.model.AlertMaker;
import sample.model.DiagnoseTable;

public class DiagnosePatientController implements Initializable {

    @FXML
    private TableView table;

    @FXML
    private TableColumn<DiagnoseTable, String> ssncol;

    @FXML
    private TableColumn<DiagnoseTable, String> firstnamecol;

    @FXML
    private TableColumn<DiagnoseTable, String> lastnamecol;

    @FXML
    private TableColumn<DiagnoseTable, String> dobcol;

    @FXML
    private TableColumn<DiagnoseTable, String> gendercol;

    @FXML
    private TableColumn<DiagnoseTable, String> diagnosiscol;

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
    private TextField diagnosistextfield;

    @FXML
    private Button addButton;

    @FXML
    private Button UpdateButton;

    @FXML
    private Button removeButton;

    public static int nurseSSN;

    private SwitchScene sc = new SwitchScene();
    private AlertMaker alerMaker = new AlertMaker();
    ObservableList<DiagnoseTable> data = FXCollections.observableArrayList();

    DiagnosQueries dq = new DiagnosQueries();
    DiagnoseTable selectedItem = new DiagnoseTable();

    @FXML
    void Add(ActionEvent event) throws SQLException {
        String text = diagnosistextfield.getText();
        if (!(text == null)) {
            selectedItem.setDiagnosis(diagnosistextfield.getText());
            dq.insertIntoDiagnosTable(diagnosistextfield.getText(), ssntextfield.getText());
            alerMaker.infoAlert("Diagonosis Added Successfully", "Successfully!");
            for (int i = 0; i < data.size(); i++) {
                String ssn = data.get(i).getSsn();
                if (ssn.equals(selectedItem.getSsn())) {
                    data.set(i, selectedItem);
                    refresh();
                }
            }

        } else {
            alerMaker.errorAlert("Some Fields are Empty", "Error!");
        }
    }

    @FXML
    void remove(ActionEvent event) throws SQLException {
        selectedItem.setDiagnosis(" ");
        dq.deleteIntoDiagnosTable(ssntextfield.getText());
        alerMaker.infoAlert("Diagonosis Removed Successfully", "Done!");
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
        selectedItem.setDiagnosis(diagnosistextfield.getText());
        dq.updateIntoDiagnosTable(diagnosistextfield.getText(), ssntextfield.getText());
        alerMaker.infoAlert("Diagonosis Updated Successfully", "Done!");
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
        sc.newScene(event, "/sample/view/nurse.fxml");
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void fectchdata(MouseEvent event) {
        selectedItem = (DiagnoseTable) table.getSelectionModel().getSelectedItem();
        ssntextfield.setText(selectedItem.getSsn());
        firstnametextfield.setText(selectedItem.getFirstName());
        lastnametextfield.setText(selectedItem.getLastName());
        gendertextfield.setText(selectedItem.getGender());
        datetextfield.setText(selectedItem.getDateOfBirth());
        diagnosistextfield.setText(selectedItem.getDiagnosis());

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

        final Tooltip tooltipDiagnosis = new Tooltip();
        tooltipDiagnosis.setText("Enter the patient Diagnosis");
        diagnosistextfield.setTooltip(tooltipDiagnosis);

        final Tooltip tooltipAddButton = new Tooltip();
        tooltipAddButton.setText("Press this button to add the diagnos");
        addButton.setTooltip(tooltipAddButton);

        final Tooltip tooltipUpdateButton = new Tooltip();
        tooltipUpdateButton.setText("Press this button to update the diagnos");
        UpdateButton.setTooltip(tooltipUpdateButton);

        final Tooltip tooltipRemoveButton = new Tooltip();
        tooltipRemoveButton.setText("Press this button to remove the diagnos");
        removeButton.setTooltip(tooltipRemoveButton);


        ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        diagnosiscol.setCellValueFactory(new PropertyValueFactory<>("Diagnosis"));

        try {
            ArrayList<DiagnoseTable> view = dq.viewDignosTable();
            data.addAll(view);
            table.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void refresh() {
        table.setItems(data);
    }
}
