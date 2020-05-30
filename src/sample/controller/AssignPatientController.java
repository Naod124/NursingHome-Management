package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

	public static String nurseSSN;

	PatientQueries pq = new PatientQueries();


	ArrayList<String> timetable = new ArrayList<>();

	@FXML
	void assign(ActionEvent event) throws SQLException {

		String updateFreeTime = updateFreeTime();
		pq.AssignNurseToPatient(ssntextfield.getText(), freetime.getValue(), nurseSSN, updateFreeTime);

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

		final Tooltip tooltipSsn = new Tooltip();
		tooltipSsn.setText("Enter the patients social security number. It shall be in this format: yymmdd****");
		ssntextfield.setTooltip(tooltipSsn);


		final Tooltip tooltipFirstname = new Tooltip();
		tooltipFirstname.setText("Enter the firstname of the patient");
		firstnametextfield.setTooltip(tooltipFirstname);


		final Tooltip tooltipLastname = new Tooltip();
		tooltipLastname.setText("Enter the lastname of the patient");
		lastnametextfield.setTooltip(tooltipLastname);


		final Tooltip tooltipdate = new Tooltip();
		tooltipdate.setText("Enter date of birth of the patient in this format: YYYY-MM-DD");
		datetextfield.setTooltip(tooltipdate);

		final Tooltip tooltipGender = new Tooltip();
		tooltipGender.setText("Enter sex of the patient. It shall be Male or Female");
		gendertextfield.setTooltip(tooltipGender);

		final Tooltip tooltipFreeTime = new Tooltip();
		tooltipFreeTime.setText("choose a free time");
		freetime.setTooltip(tooltipFreeTime);


		try {
			handleView();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void handleView() throws SQLException {
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
		Object selectedItem = table.getSelectionModel().getSelectedItem();
		PatientTable table = (PatientTable) selectedItem;

		ssntextfield.setText(table.getSsn());
		firstnametextfield.setText(table.getFirstName());
		lastnametextfield.setText(table.getLastName());
		gendertextfield.setText(table.getGender());
		datetextfield.setText(table.getDateOfBirth());

		ssntextfield.setEditable(false);
		firstnametextfield.setEditable(false);
		lastnametextfield.setEditable(false);
		gendertextfield.setEditable(false);
		datetextfield.setEditable(false);


		timetable.clear();

		String[] split = table.getFreeTime().split(",");
		if (split.length != 0) {
			for (int i = 0; i < split.length; i++) {
				timetable.add(split[i]);
			}
			freetime.getItems().clear();
			freetime.getItems().addAll(timetable);
		} else {
			freetime.getItems().clear();
			// setting no time Available if empty
			freetime.getItems().addAll("No Time Available");
		}

	}

	public String updateFreeTime() {
		String remainingfreetime = "";
		timetable.remove(freetime.getValue());
		for (int i = 0; i < timetable.size(); i++) {
			remainingfreetime += timetable.get(i) + ",";
		}
		return remainingfreetime;
	}
}
