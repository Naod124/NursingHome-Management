package sample.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sample.model.DiagnoseTable;
import sample.model.VisitTable;

public class VisitQueries {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public VisitQueries() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
					"nursinghome", "Vw3J!60l-0kd");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// insert query for adding diagnosis
	public void insertIntoTable(String visitor, String SSN, String visitortime, String Freetime) throws SQLException {

		connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
				"Vw3J!60l-0kd");

		// query for inserting visitor into patient table
		String insertQuery = "UPDATE patient SET visitor = ?, visitortime = ? WHERE SSN = ?;";
		PreparedStatement pstmt = connection.prepareStatement(insertQuery);
		pstmt.setString(1, visitor);
		pstmt.setString(2, visitortime);
		pstmt.setString(3, SSN);

		pstmt.executeUpdate();

		// update the free time of patient
		String updateQuery = "UPDATE patient SET FreeTime = ? WHERE SSN = ?;";
		PreparedStatement pstmt1 = connection.prepareStatement(updateQuery);
		pstmt1.setString(1, Freetime);
		pstmt1.setString(2, SSN);
		pstmt1.executeUpdate();

	}

	// Update query for adding diagnosis
	public void UpdateIntoTable(String visitor, String SSN, String visitortime, String Freetime) throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
				"Vw3J!60l-0kd");

		// query for inserting visitor into patient table
		String insertQuery = "UPDATE patient SET visitor = ?, visitortime = ? WHERE SSN = ?;";
		PreparedStatement pstmt = connection.prepareStatement(insertQuery);
		pstmt.setString(1, visitor);
		pstmt.setString(2, visitortime);
		pstmt.setString(3, SSN);

		pstmt.executeUpdate();

		// update the free time of patient
		String updateQuery = "UPDATE patient SET FreeTime = ? WHERE SSN = ?;";
		PreparedStatement pstmt1 = connection.prepareStatement(updateQuery);
		pstmt1.setString(1, Freetime);
		pstmt1.setString(2, SSN);
		pstmt1.executeUpdate();

	}

	// for getting item from the database
	public ArrayList<VisitTable> viewTable() throws SQLException {
		ArrayList<VisitTable> list = new ArrayList<>();
		// select query for getting data from patient and their diagnoses from diagnose
		// table
		String selectQuery = "SELECT * FROM patient;";

		try {
			connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
					"nursinghome", "Vw3J!60l-0kd");
			resultSet = connection.createStatement().executeQuery(selectQuery);

			while (resultSet.next()) {
				// creating object and setting data
				VisitTable visit = new VisitTable("", "", "", "", "", "", "", "");
				visit.setDateOfBirth(resultSet.getString("DateOfBirth"));
				visit.setVisitor(resultSet.getString("visitor"));
				visit.setFirstName(resultSet.getString("FirstName"));
				visit.setFreeTime(resultSet.getString("FreeTime"));
				visit.setGender(resultSet.getString("Gender"));
				visit.setLastName(resultSet.getString("LastName"));
				visit.setSsn(resultSet.getString("SSN"));
				visit.setVisitortime(resultSet.getString("visitortime"));

				list.add(visit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// for delete item from the database
	public void deleteIntoTable(String SSN) throws SQLException {

		connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
				"Vw3J!60l-0kd");

		String updateQuery = "UPDATE patient SET visitor = '', visitortime = '' where SSN = ?;";

		PreparedStatement pstmt1 = connection.prepareStatement(updateQuery);
		pstmt1.setString(1, SSN);
		pstmt1.executeUpdate();
	}
}
