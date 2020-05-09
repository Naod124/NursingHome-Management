package sample.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.databaseConnection.PatientQueries;
import sample.model.PatientTable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class RemovePatientController implements Initializable {

    ManagePatientController mp = new ManagePatientController();

    private Connection conn;
    PreparedStatement pstmt;


    @FXML private TableView<Object> table;
    @FXML private TableColumn<PatientTable, String> ssncol;
    @FXML private TableColumn <PatientTable, String> firstnamecol;
    @FXML private TableColumn <PatientTable, String> lastnamecol;
    @FXML private TableColumn <PatientTable, String> dobcol;
    @FXML private TableColumn <PatientTable, String> gendercol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            seepatient();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 public void seepatient() throws SQLException {
     ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
     firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
     lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
     dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
     gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
     PatientQueries pq = new PatientQueries();
     pq.viewPatientTable();

     table.setItems(pq.getObList());


 }
    @FXML public void deletePatient() throws SQLException {

       try {
           PatientQueries pq = new PatientQueries();
           PatientTable pt = (PatientTable) table.getSelectionModel().getSelectedItem();
           pq.removePatient( pt.getSsn());
           Alert a = new Alert(Alert.AlertType.CONFIRMATION);
           a.setHeaderText("Patient removed");
           a.showAndWait();
           table.getItems().clear();
           seepatient();
       }
       catch (SQLException e){
           Alert a = new Alert(Alert.AlertType.ERROR);
           a.setHeaderText("ERROR");
           a.showAndWait();

       }


    }

    @FXML
    public void back(ActionEvent ae) throws IOException {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/nurse.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("Hi");
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void exit(ActionEvent ae) throws IOException {
        System.exit(0);

    }



}
