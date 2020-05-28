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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.databaseConnection.DiagnosQueries;
import sample.databaseConnection.MedcinQueries;
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
    private TableColumn<DiagnoseTable, String> mediciencol;

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
    private TextField medicinetextfield;

    @FXML
    private TextField diagnosistextfield;
    public static int nurseSSN;

    private SwitchScene sc = new SwitchScene();
    ObservableList<DiagnoseTable> data = FXCollections.observableArrayList();

    DiagnosQueries dq = new DiagnosQueries();

    MedcinQueries mq = new MedcinQueries();
    DiagnoseTable selectedItem = new DiagnoseTable();

    @FXML
    void Add(ActionEvent event) throws SQLException {
        String text = diagnosistextfield.getText();
        String text2 = medicinetextfield.getText();



        if (text == null && text2 == null) {
            new Alert(Alert.AlertType.ERROR, "Some Fields are Empty").showAndWait();
        }

        if (!(text2 == null)) {
            selectedItem.setMedicien(medicinetextfield.getText());
            mq.insertMedicine(medicinetextfield.getText(), ssntextfield.getText());
            new Alert(Alert.AlertType.INFORMATION, "Medicine Added Successfully").showAndWait();
            for (int i = 0; i < data.size(); i++) {
                String ssn = data.get(i).getSsn();
                if (ssn.equals(selectedItem.getSsn())) {
                    data.set(i, selectedItem);
                    refresh();
                }
            }
        }

        if (!(text == null)) {
            selectedItem.setDiagnosis(diagnosistextfield.getText());
            dq.insertIntoDiagnosTable(diagnosistextfield.getText(), ssntextfield.getText());
            new Alert(Alert.AlertType.INFORMATION, "Diagonosis Added Successfully").showAndWait();
            for (int i = 0; i < data.size(); i++) {
                String ssn = data.get(i).getSsn();
                if (ssn.equals(selectedItem.getSsn())) {
                    data.set(i, selectedItem);
                    refresh();
                }
            }

        }
    }

    @FXML
    void remove(ActionEvent event) throws SQLException {
        selectedItem.setDiagnosis(" ");
        selectedItem.setMedicien(" ");
        dq.deleteIntoDiagnosTable(ssntextfield.getText());
        mq.removeMedicine(ssntextfield.getText());
        new Alert(Alert.AlertType.INFORMATION, "Removed Successfully").showAndWait();
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

        String text = diagnosistextfield.getText();
        String text2 = medicinetextfield.getText();

        if (!(text2 == null)) {
            selectedItem.setMedicien(medicinetextfield.getText());
            mq.updateMedicine(medicinetextfield.getText(), ssntextfield.getText());
            new Alert(Alert.AlertType.INFORMATION, "Medicine Updated Successfully").showAndWait();
            for (int i = 0; i < data.size(); i++) {
                String ssn = data.get(i).getSsn();
                if (ssn.equals(selectedItem.getSsn())) {
                    data.set(i, selectedItem);
                    refresh();
                }
            }
        }

        if (!(text == null)) {
            selectedItem.setDiagnosis(diagnosistextfield.getText());
            dq.updateIntoDiagnosTable(diagnosistextfield.getText(), ssntextfield.getText());
            new Alert(Alert.AlertType.INFORMATION, "Diagonosis Updated Successfully").showAndWait();
            for (int i = 0; i < data.size(); i++) {
                String ssn = data.get(i).getSsn();
                if (ssn.equals(selectedItem.getSsn())) {
                    data.set(i, selectedItem);
                    refresh();
                }
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
        medicinetextfield.setText(selectedItem.getMedicien());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        diagnosiscol.setCellValueFactory(new PropertyValueFactory<>("Diagnosis"));
        mediciencol.setCellValueFactory(new PropertyValueFactory<>("Medicien"));
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
