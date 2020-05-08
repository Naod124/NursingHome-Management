package sample.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.Connect;
import sample.databaseConnection.PatientQueries;
import sample.model.PatientTable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {
    @FXML private TableView<PatientTable> patientScheduleTableView;
    @FXML private TableColumn<PatientTable, String> firstName_lastName;
    @FXML private TableColumn<PatientTable, String> timeFrom;
    @FXML private TableColumn<PatientTable, String> timeTO;
    @FXML private TableColumn<PatientTable, String> description;

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
}
