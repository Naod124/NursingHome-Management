package sample.controller;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import sample.databaseConnection.PatientQueries;
import sample.model.ExportToPdf;
import sample.model.PatientTable;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {
    @FXML private TableView<PatientTable> patientScheduleTableView;
    @FXML private TableColumn<PatientTable, String> firstName_lastName;
    @FXML private TableColumn<PatientTable, String> timeFrom;
    @FXML private TableColumn<PatientTable, String> timeTO;
    @FXML private TableColumn<PatientTable, String> description;
    public String path;
    SwitchScene sc = new SwitchScene();


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

    }

    public ObservableList<PatientTable> getPatients() throws SQLException {

        PatientQueries pq = new PatientQueries();
        pq.scheduleView();
                    return pq.getPatients();

    }

    public void ScheduleToPdf() {
        FileChooser fc = new FileChooser();
        File f = fc.showSaveDialog(null);
        if (f != null) {
            path = f.getAbsolutePath();
           ObservableList<PatientTable> p = patientScheduleTableView.getItems();
            ExportToPdf pdf = new ExportToPdf();
            pdf.createSchedulePdf(p, path);
        }
    }

    public void backTo(ActionEvent ae) throws IOException {
        if (LogInPanelController.role.equals("planer")) {
            sc.newScene(ae, "/sample/view/planer.fxml");
        }else {
            sc.newScene(ae, "/sample/view/nurse.fxml");
        }

    }
}
