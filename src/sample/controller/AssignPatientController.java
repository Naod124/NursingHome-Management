package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.databaseConnection.PatientQueries;
import sample.model.PatientTable;

public class AssignPatientController implements Initializable {

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
	private ComboBox<String> freetime;

	// to keep track of nurse that is login
	public static int nurseSSN;

	PatientQueries pq = new PatientQueries();

	// keeping record of patient free time
	ArrayList<String> timetable = new ArrayList<>();

	@FXML
	void assign(ActionEvent event) throws SQLException {

		// get the remaining free time of the patient
		String updateFreeTime = updateFreeTime();
		// Adding or Asigning the patient time by nurse
		pq.AssignNursetoPatient(ssntextfield.getText(), freetime.getValue(), String.valueOf(nurseSSN), updateFreeTime);

		new Alert(Alert.AlertType.INFORMATION, "Paitient Assigned Successfully").showAndWait();

	}

	public void exit() {
		System.exit(0);
	}

	public void back(ActionEvent event) throws IOException {
		sc.newScene(event, "/sample/view/nurse.fxml");
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
		// setting the table columns
		ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
		firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
		gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		pq.viewPatientTable();
		table.setItems(pq.getObList());

	}

	@FXML
	void fectchdata(MouseEvent event) throws SQLException {
		// getting the selected data from table while clicking on the row
		Object selectedItem = table.getSelectionModel().getSelectedItem();
		// casting that object to patient table to access the different information or
		// data
		PatientTable table = (PatientTable) selectedItem;

		// setting the fields with information that is selected
		ssntextfield.setText(table.getSsn());
		firstnametextfield.setText(table.getFirstName());
		lastnametextfield.setText(table.getLastName());
		gendertextfield.setText(table.getGender());
		datetextfield.setText(table.getDateOfBirth());

		timetable.clear();
		// getting the free time of patient from database and spliting with respect to
		// comma
		String[] split = table.getFreeTime().split(",");
		// now adding splited data into the timetable list
		if (split.length != 0) {
			for (int i = 0; i < split.length; i++) {
				timetable.add(split[i]);
			}
			// clearing the combo-box item
			freetime.getItems().clear();
			// adding the time available to the combo-box
			freetime.getItems().addAll(timetable);
		} else {
			freetime.getItems().clear();
			// setting no time Available if empty
			freetime.getItems().addAll("No Time Available");
		}

	}

	// updating free time of the patient
	public String updateFreeTime() {
		String remainingfreetime = "";
		timetable.remove(freetime.getValue());
		for (int i = 0; i < timetable.size(); i++) {
			remainingfreetime += timetable.get(i) + ",";
		}
		return remainingfreetime;
	}
}
