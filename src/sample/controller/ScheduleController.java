package sample.controller;


import com.sun.glass.ui.CommonDialogs;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import sample.databaseConnection.PatientQueries;
import sample.model.ExportToPdf;
import sample.model.PatientTable;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {
    @FXML private TableView<PatientTable> patientScheduleTableView;
    @FXML private TableColumn<PatientTable, String> firstName_lastName;
    @FXML private TableColumn<PatientTable, String> timeFrom;
    @FXML private TableColumn<PatientTable, String> timeTO;
    @FXML private TableColumn<PatientTable, String> description;
    @FXML private Button previousPage;
    @FXML private Button deletePatient;
    @FXML private Button pdfButton;
    public String path;
    public List<String> lis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstName_lastName.setCellValueFactory(new PropertyValueFactory<PatientTable, String>("fullName"));
        timeFrom.setCellValueFactory(new PropertyValueFactory<PatientTable, String>("timeTo"));
        timeTO.setCellValueFactory(new PropertyValueFactory<PatientTable, String>("timeFrom"));
        description.setCellValueFactory(new PropertyValueFactory<PatientTable, String>("description"));


        try {
            patientScheduleTableView.setItems(getPatients());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throwables.getMessage();
        }
        ObservableList<PatientTable> p = patientScheduleTableView.getItems();
        System.out.println(p);
    }

    public ObservableList<PatientTable> getPatients() throws SQLException {
       /* ObservableList<PatientTable> patients = FXCollections.observableArrayList();
        Connection conn = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM schedule;");
        while (rs.next()) {
            patients.add(new PatientTable(rs.getString("patient_name")
                    ,rs.getString("time_from"),rs.getString("time_to")
                    ,rs.getString("description")));

        }*/

        PatientQueries pq = new PatientQueries();
        pq.scheduleView();
                    return pq.getPatients();

    }

  /*  public void deleteSelectedSchedule() {
        try {
            PatientTable result = patientScheduleTableView.getSelectionModel().getSelectedItem();
            PatientQueries pq = new PatientQueries();
            pq.removeSelectedPatientSchedule(result.getFullName(), result.getTimeFrom(), result.getTimeTo(), result.getDescription());
            System.out.println(result.getFullName()+" "+result.getTimeFrom()+" "+result.getTimeTo()+" "+result.getDescription());
            System.out.println(result.getTimeFrom());
        }catch(SQLException s){
            System.out.println(s.getMessage());
        }

    }
*/
    public void ScheduleToPdf() {
        FileChooser fc = new FileChooser();
        File f = fc.showSaveDialog(null);
        if (f != null) {
            path = f.getAbsolutePath();
            System.out.println(path);
           ObservableList<PatientTable> p = patientScheduleTableView.getItems();
            ExportToPdf pdf = new ExportToPdf();
            pdf.createSchedulePdf(p, path);
        }
    }

    public void backTo() {

    }
}
