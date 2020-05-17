package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.PatientQueries;
import sample.model.DataSource;
import sample.model.PatientTable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AddPatientController implements Initializable {


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
    private TextField ssntextfield;
    @FXML
    private TextField firstnametextfield;
    @FXML
    private TextField lastnametextfield;
    @FXML
    private TextField datetextfield;
    @FXML
    private TextField gendertextfield;

    @FXML
    private Button aTozButton;

    @FXML
    private Button zToaButton;
    @FXML
    private Button addButton;

    private SwitchScene sc = new SwitchScene();


    public void refreshDataSource() {
        try {
            PatientQueries p = new PatientQueries();
            p.viewPatientTable();

            DataSource.getInstance().setPatient(p.getPatientsinfo());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        final Tooltip tooltipAdd = new Tooltip();
        tooltipAdd.setText("Press this button after you har filled up all information to add this patient");
        addButton.setTooltip(tooltipAdd);

        final Tooltip tooltipZtoA = new Tooltip();
        tooltipZtoA.setText("Press this button to sort the table from Z-A by patients First name ");
        zToaButton.setTooltip(tooltipZtoA);

        final Tooltip tooltipAtoZ = new Tooltip();
        tooltipAtoZ.setText("Press this button to sort the table from A-Z by patients First name");
        aTozButton.setTooltip(tooltipAtoZ);

        try {
            viewPatient();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleAdd(ActionEvent ae) {
        try {
            PatientQueries pq = new PatientQueries();
            pq.insertIntoPatientTable(ssntextfield.getText(), firstnametextfield.getText(),
                    lastnametextfield.getText(), datetextfield.getText(), gendertextfield.getText());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Information added");
            a.showAndWait();
            table.getItems().clear();
            viewPatient();
            ssntextfield.clear();
            firstnametextfield.clear();
            lastnametextfield.clear();
            datetextfield.clear();
            gendertextfield.clear();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("Adding a patient did not go through!"+"\n"+"Please try again...");
        }
    }




        public void viewPatient() throws SQLException {

            rows();
            PatientQueries pq = new PatientQueries();
            pq.viewPatientTable();

            table.setItems(pq.getObList());

        }

    @FXML public void sorter() throws SQLException {
        rows();
        PatientQueries pq = new PatientQueries();
        pq.sortByName();
        table.setItems(pq.getObzList());

    }

    @FXML public void zToAsort() throws SQLException {
        rows();
        PatientQueries pq = new PatientQueries();
        pq.sortZtoA();
        table.setItems(pq.getObfList());

    }

    private void rows() throws SQLException {
        ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));

    }





    @FXML
    public void back(ActionEvent ae) throws IOException {
        sc.newScene(ae,"/sample/view/nurse.fxml");
    }

    @FXML
    public void exit(ActionEvent ae) throws IOException {
        System.exit(0);

    }

    public void help(ActionEvent actionEvent) {
    }
}