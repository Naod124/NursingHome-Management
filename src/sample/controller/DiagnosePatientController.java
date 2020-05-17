package sample.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.databaseConnection.DiagnosQueries;
import sample.model.DiagnoseTable;

public class DiagnosePatientController implements Initializable {

	@FXML
	private TableView table;

	@FXML
	private TableColumn<DiagnoseTable, String> ssncol;

	@FXML
	private TableColumn<DiagnoseTable, String> firstnamecol;

	@FXML
	private TableColumn<DiagnoseTable, String> lastnamecol;

	@FXML
	private TableColumn<DiagnoseTable, String> dobcol;

	@FXML
	private TableColumn<DiagnoseTable, String> gendercol;

	@FXML
	private TableColumn<DiagnoseTable, String> diagnosiscol;

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
	private TextField diagnosistextfield;
    // to keep track of nurse SSN
	public static int nurseSSN;

	private SwitchScene sc = new SwitchScene();
    // for storing data fetch from database 
	ObservableList<DiagnoseTable> data = FXCollections.observableArrayList();

	DiagnosQueries dq = new DiagnosQueries();
	// for keeping record of selected data
	DiagnoseTable selectedItem = new DiagnoseTable();

	@FXML
	void Add(ActionEvent event) throws SQLException {
		String text = diagnosistextfield.getText();
		// checking if the field is empty before inserting diagnose
		if (!(text == null)) {
			// setting the diagnoses of the selected item
			selectedItem.setDiagnosis(diagnosistextfield.getText());
			// inserting data into the database
			dq.insertIntoDiagnosTable(diagnosistextfield.getText(), ssntextfield.getText());
			// showing alert
			new Alert(Alert.AlertType.INFORMATION, "Diagonosis Added Successfully").showAndWait();
			for (int i = 0; i < data.size(); i++) {
				// getting the SSN of selected table data to change field of diagnose
				String ssn = data.get(i).getSsn();
				if (ssn.equals(selectedItem.getSsn())) {
					// updating the diagnose field present in table data
					data.set(i, selectedItem);
					// refreshing table
					refresh();
				}
			}

		} else {
			new Alert(Alert.AlertType.ERROR, "Some Fields are Empty").showAndWait();
		}
	}

	@FXML
	void remove(ActionEvent event) throws SQLException {
		// updating the diagnose field of selected data to null because we are going to remove it from database
		selectedItem.setDiagnosis(" ");
		// query for removing the the data in database
		dq.deleteIntoDiagnosTable(ssntextfield.getText());
		// showing alert
		new Alert(Alert.AlertType.INFORMATION, "Diagonosis Removed Successfully").showAndWait();
		for (int i = 0; i < data.size(); i++) {
			// getting the SSN of selected table data to change field of diagnose
			String ssn = data.get(i).getSsn();
			if (ssn.equals(selectedItem.getSsn())) {
				// updating the diagnose field present in table data
				data.set(i, selectedItem);
				// refreshing table
				refresh();
			}
		}
	}

	@FXML
	void update(ActionEvent event) throws SQLException {
		// updating the diagnose field of selected data
		selectedItem.setDiagnosis(diagnosistextfield.getText());
		// query for updating the data in database
		dq.updateIntoDiagnosTable(diagnosistextfield.getText(), ssntextfield.getText());
		// showing alert
		new Alert(Alert.AlertType.INFORMATION, "Diagonosis Updated Successfully").showAndWait();
		for (int i = 0; i < data.size(); i++) {
			// getting the SSN of selected table data to change field of diagnose
			String ssn = data.get(i).getSsn();
			if (ssn.equals(selectedItem.getSsn())) {
				// updating the diagnose field present in table data
				data.set(i, selectedItem);
				// refreshing table
				refresh();
			}
		}
	}

	@FXML
	void back(ActionEvent event) throws IOException {
		sc.newScene(event, "/sample/view/nurse.fxml");
	}

	@FXML
	void exit(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void fectchdata(MouseEvent event) {
		// getting the selected data from table while clicking on the row
		selectedItem = (DiagnoseTable) table.getSelectionModel().getSelectedItem();
		// setting the fields with information that is selected
		ssntextfield.setText(selectedItem.getSsn());
		firstnametextfield.setText(selectedItem.getFirstName());
		lastnametextfield.setText(selectedItem.getLastName());
		gendertextfield.setText(selectedItem.getGender());
		datetextfield.setText(selectedItem.getDateOfBirth());
		diagnosistextfield.setText(selectedItem.getDiagnosis());

	}

	// method for initializing table and getting data from database
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// setting the table columns
		ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
		firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
		gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		diagnosiscol.setCellValueFactory(new PropertyValueFactory<>("Diagnosis"));

		try {
			// getting data from database
			ArrayList<DiagnoseTable> view = dq.viewDignosTable();
			data.addAll(view);
			// setting data to table
			table.setItems(data);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// method to refresh change in data after different operation
	public void refresh() {
		table.setItems(data);
	}

}
