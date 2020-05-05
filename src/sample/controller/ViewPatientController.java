package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.PatientTable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewPatientController implements Initializable {
    @FXML
    private TableView<PatientTable> table;
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
    private SwitchScene sc = new SwitchScene();
    private ObservableList<PatientTable> obList = FXCollections.observableArrayList();

    public void exit() {
        System.exit(0);
    }

    public void back(ActionEvent actionEvent) throws IOException {
        sc.newScene(actionEvent, "/sample/view/admin.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            handleView();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleView() throws SQLException {
        ResultSet rs;
        String selectQuery = "SELECT * FROM PATIENT;";

        Connection conn = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                "nursinghome", "Vw3J!60l-0kd");
        rs = conn.createStatement().executeQuery(selectQuery);


        ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));


        while (rs.next()) {
            PatientTable pt = new PatientTable("SSN", "FirstName", "LastName", "DateOfBirth", "Gender");


            pt.setSsn(rs.getString("SSN"));
            pt.setFirstName(rs.getString("FirstName"));
            pt.setLastName(rs.getString("LastName"));
            pt.setDateOfBirth(rs.getString("DateOfBirth"));
            pt.setGender(rs.getString("Gender"));

            obList.add(pt);

        }

        table.setItems(obList);
    }

}
