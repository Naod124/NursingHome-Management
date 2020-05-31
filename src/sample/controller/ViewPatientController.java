package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.PatientQueries;
import sample.model.PatientTable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewPatientController implements Initializable {
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
    private SwitchScene sc = new SwitchScene();

    public void exit() {
        System.exit(0);
    }

    public void back(ActionEvent actionEvent) throws IOException {
        if (LogInPanelController.role.equals("admin")) {
            sc.newScene(actionEvent, "/sample/view/admin.fxml");
        }else if (LogInPanelController.role.equals("nurse")) {
            sc.newScene(actionEvent, "/sample/view/nurse.fxml");
        }else {
            sc.newScene(actionEvent, "/sample/view/planer.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            handleView();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @FXML
    public void handleView() throws SQLException {

        ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
        firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        PatientQueries pq = new PatientQueries();
        pq.viewPatientTable();

        table.setItems(pq.getObList());

    }

}
