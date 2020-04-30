package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.model.PatientTable;

import java.sql.SQLException;

public class RemovePatientController {

    ManagePatientController mp = new ManagePatientController();


    @FXML private TableView<PatientTable> table;


    @FXML private Button deletebutton;
    @FXML private Button viewbutton;
    @FXML private TextField ssntextfield;
    @FXML private TableColumn<PatientTable, String> ssncol;
    @FXML private TableColumn <PatientTable, String> firstnamecol;
    @FXML private TableColumn <PatientTable, String> lastnamecol;
    @FXML private TableColumn <PatientTable, String> dobcol;
    @FXML private TableColumn <PatientTable, String> gendercol;




    @FXML public void removePatient() throws SQLException {

        mp.handledelete();
    }



}
