package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.PatientQueries;
import sample.model.PatientTable;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditPatientInfo implements Initializable {

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
    private Button updateButton;
    @FXML
    private Button aTozbutton;

    @FXML
    private Button zToaButton;

    private SwitchScene sc = new SwitchScene();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


       final Tooltip tooltipSsn = new Tooltip();
        tooltipSsn.setText("Enter the patients social security number that you want to modify. It shall be in this format: yymmdd****");
        ssntextfield.setTooltip(tooltipSsn);


        final Tooltip tooltipFirstname = new Tooltip();
        tooltipFirstname.setText("Enter the firstname of the patient to edit");
        firstnametextfield.setTooltip(tooltipFirstname);


        final Tooltip tooltipLastname = new Tooltip();
        tooltipLastname.setText("Enter the lastname of the patient to edit");
        lastnametextfield.setTooltip(tooltipLastname);


        final Tooltip tooltipdate = new Tooltip();
        tooltipdate.setText("Enter date of birth of the patient to edit in this format: YYYY-MM-DD " );
        datetextfield.setTooltip(tooltipdate);

        final Tooltip tooltipGender = new Tooltip();
        tooltipGender.setText("Enter sex of the patient to edit. It shall be Male or Female");
        gendertextfield.setTooltip(tooltipGender);

        final Tooltip tooltipAdd = new Tooltip();
        tooltipAdd.setText("Press this button after you har filled up all information to update the patient info");
        updateButton.setTooltip(tooltipAdd);

        final Tooltip tooltipZtoA = new Tooltip();
        tooltipZtoA.setText("Press this button to sort the table from Z-A by patients First name ");
        zToaButton.setTooltip(tooltipZtoA);

        final Tooltip tooltipAtoZ = new Tooltip();
        tooltipAtoZ.setText("Press this button to sort the table from A-Z by patients First name");
        aTozbutton.setTooltip(tooltipAtoZ);



        try {
            checkPatient();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    @FXML
    public void handleUpdate() {

        try {
            PatientQueries pq = new PatientQueries();
            table.getSelectionModel().getSelectedItem();
            pq.updateIntoPatientTable(firstnametextfield.getText(), lastnametextfield.getText(),
                    datetextfield.getText(), gendertextfield.getText(), ssntextfield.getText());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setHeaderText("Information updated");
            a.showAndWait();
            table.getItems().clear();
            checkPatient();
            ssntextfield.clear();
            firstnametextfield.clear();
            lastnametextfield.clear();
            datetextfield.clear();
            gendertextfield.clear();

        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("updating a patient did not go through!"+"\n"+"Please try again...");
        }

    }

    public void checkPatient() throws SQLException {


        cellFacturies();
        PatientQueries pq = new PatientQueries();
        pq.viewPatientTable();

        table.setItems(pq.getObList());

    }
    @FXML public void sorter() throws SQLException {
         cellFacturies();
       PatientQueries pq = new PatientQueries();
        pq.sortByName();
        table.setItems(pq.getObzList());

    }

    @FXML public void zToAsort() throws SQLException {
        cellFacturies();
        PatientQueries pq = new PatientQueries();
        pq.sortZtoA();
        table.setItems(pq.getObfList());

    }

    private void cellFacturies() throws SQLException {
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
}