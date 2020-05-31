package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.PatientQueries;
import sample.model.AlertMaker;
import sample.model.PatientTable;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RemovePatientController implements Initializable {


    @FXML
    private TableView<Object> table;
    @FXML
    private TableColumn<PatientTable, String> ssncol;
    @FXML
    private TableColumn<PatientTable, String> firstnamecol;
    @FXML
    private TableColumn<PatientTable, String> lastnamecol;
    @FXML
    private TableColumn<PatientTable, String> dobcol;
    @FXML
    private TableColumn<PatientTable, String> gendercol;
    @FXML
    private Button removeButton;

    private AlertMaker alerMaker = new AlertMaker();
    private SwitchScene sc = new SwitchScene();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        final Tooltip tooltipTable = new Tooltip();
        tooltipTable.setText("Press on the row you want to delete");
        table.setTooltip(tooltipTable);


        final Tooltip tooltipRemoveButton = new Tooltip();
        tooltipRemoveButton.setText("Press this button to remove the selected row");
        removeButton.setTooltip(tooltipRemoveButton);

        try {
            seePatient();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void seePatient() throws SQLException {
        rows();
        PatientQueries pq = new PatientQueries();
        pq.viewPatientTable();

        table.setItems(pq.getObList());


    }

    @FXML
    public void deletePatient() throws SQLException {

        try {
            PatientQueries pq = new PatientQueries();
            if (!(table.getSelectionModel().getSelectedItem() == null)){
                PatientTable pt = (PatientTable) table.getSelectionModel().getSelectedItem();
                pq.removePatient(pt.getSsn());
                alerMaker.confirmAlert("Patient removed","Successfully!");
                table.getItems().clear();
                seePatient();
            }
            else {
                alerMaker.errorAlert("You have not selected anything", "Error");
            }

        } catch (SQLException e) {
            alerMaker.errorAlert("", "ERROR");
            System.out.println(e.getMessage());
        }
    }


    public void rows() {
        ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));

    }


    @FXML
    public void back(ActionEvent ae) throws IOException {
        if (LogInPanelController.role.equals("nurse")) {
            sc.newScene(ae, "/sample/view/nurse.fxml");
        }else {
            sc.newScene(ae, "/sample/view/planer.fxml");
        }
    }

    @FXML
    public void exit(ActionEvent ae) throws IOException {
        System.exit(0);

    }


}
