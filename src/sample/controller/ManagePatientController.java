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

public class ManagePatientController implements Initializable {
    private Connection conn;
    PreparedStatement pstmt = null;
    private ResultSet rs;

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
        }
    }


    @FXML
    public void handleupdate() {

        try {
            PatientQueries pq = new PatientQueries();
            pq.updateIntoPatientTable(firstnametextfield.getText(), lastnametextfield.getText(),
                    datetextfield.getText(), gendertextfield.getText(), ssntextfield.getText());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Information updated");
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
        }
    }
    public void viewPatient() throws SQLException {


        ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        PatientQueries pq = new PatientQueries();
        pq.viewPatientTable();

        table.setItems(pq.getObList());

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