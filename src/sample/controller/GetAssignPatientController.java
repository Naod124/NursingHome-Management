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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.databaseConnection.PatientQueries;
import sample.model.AssignTable;

public class GetAssignPatientController implements Initializable {

	@FXML
	private TableView<AssignTable> table;

	@FXML
	private TableColumn<AssignTable, String> ssncol;

	@FXML
	private TableColumn<AssignTable, String> firstnamecol;

	@FXML
	private TableColumn<AssignTable, String> lastnamecol;

	@FXML
	private TableColumn<AssignTable, String> dobcol;

	@FXML
	private TableColumn<AssignTable, String> gendercol;

	@FXML
	private TableColumn<AssignTable, String> appointcol;

	@FXML
	private TableColumn<AssignTable, String> occupaidcol;

	private SwitchScene sc = new SwitchScene();

	PatientQueries pq = new PatientQueries();

	ObservableList<AssignTable> data = FXCollections.observableArrayList();

	public void exit() {
		System.exit(0);
	}

	public void back(ActionEvent event) throws IOException {
		sc.newScene(event, "/sample/view/nurse.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ssncol.setCellValueFactory(new PropertyValueFactory<>("Ssn"));
		firstnamecol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		lastnamecol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		dobcol.setCellValueFactory(new PropertyValueFactory<>("DateOfBirth"));
		gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		appointcol.setCellValueFactory(new PropertyValueFactory<>("Appointed_time"));
		occupaidcol.setCellValueFactory(new PropertyValueFactory<>("occupiedby"));

		try {
			ArrayList<AssignTable> view = pq.viewAssigneTable();
			data.addAll(view);
			table.setItems(data);
		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}

	}

}