package sample.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import sample.model.DiagnoseTable;

public class DiagnosQueries {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DiagnosQueries() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    // insert query for adding diagnosis
    public void insertIntoDiagnosTable(String diagnose, String SSN) throws SQLException {
        // keep track of diagnosis id that is added on diagnose table
        int insertid = 0;
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");

        // query for inserting data
        String insertQuery = "INSERT INTO diagnos (DiagnosType) VALUES(?);";
        // query  getting id of newly inserted data
        String idQuery = "select max(idDiagnos) from diagnos;";

        PreparedStatement pstmt = connection.prepareStatement(insertQuery);
        PreparedStatement pstmt1 = connection.prepareStatement(idQuery);

        pstmt.setString(1, diagnose);
        pstmt.executeUpdate();

        resultSet = pstmt1.executeQuery();
        while (resultSet.next()) {
            // getting id of newly inserted data
            insertid = resultSet.getInt("max(idDiagnos)");
        }

        // query for inserting id into patient table
        String updateQuery = "UPDATE patient SET diagnoseid = " + insertid + " WHERE SSN = '" + SSN + "';";
        PreparedStatement pstmt2 = connection.prepareStatement(updateQuery);
        pstmt2.executeUpdate();

    }

    // update query for adding diagnosis
    public void updateIntoDiagnosTable(String diagnose, String SSN) throws SQLException {
        int id = 0;
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");
        // query  of getting id of diagnose with respect to patient
        String idQuery = "SELECT diagnoseid FROM patient where SSN = ?";
        PreparedStatement pstmt1 = connection.prepareStatement(idQuery);
        pstmt1.setString(1, SSN);

        resultSet = pstmt1.executeQuery();
        while (resultSet.next()) {
            // getting id of diagnose
            id = resultSet.getInt("diagnoseid");
        }

        // query UPDATE  diagnose type in diagnose table
        String insertQuery = "UPDATE diagnos SET DiagnosType = ? WHERE idDiagnos = ? ";

        PreparedStatement pstmt = connection.prepareStatement(insertQuery);

        pstmt.setString(1, diagnose);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }

    // for getting item from the database
    public ArrayList<DiagnoseTable> viewDignosTable() throws SQLException {
        ArrayList<DiagnoseTable> list = new ArrayList<>();
        // select query for getting data from patient and their diagnoses from diagnose table
        String selectQuery = "SELECT * FROM patient LEFT OUTER JOIN diagnos ON patient.diagnoseid = diagnos.idDiagnos;";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
            resultSet = connection.createStatement().executeQuery(selectQuery);

            while (resultSet.next()) {
                // creating object and setting data
                DiagnoseTable diagnose = new DiagnoseTable("", "", "", "", "", "", "");
                diagnose.setDateOfBirth(resultSet.getString("DateOfBirth"));
                diagnose.setDiagnosis(resultSet.getString("DiagnosType"));
                diagnose.setFirstName(resultSet.getString("FirstName"));
                diagnose.setFreeTime(resultSet.getString("FreeTime"));
                diagnose.setGender(resultSet.getString("Gender"));
                diagnose.setLastName(resultSet.getString("LastName"));
                diagnose.setSsn(resultSet.getString("SSN"));
                list.add(diagnose);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // for delete item from the database
    public void deleteIntoDiagnosTable(String SSN) throws SQLException {
        int id = 0;
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");
        // query  of getting id of diagnose with respect to patient
        String idQuery = "SELECT diagnoseid FROM patient where SSN = ?";
        PreparedStatement pstmt = connection.prepareStatement(idQuery);
        pstmt.setString(1, SSN);

        resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            // getting id of diagnose
            id = resultSet.getInt("diagnoseid");
        }

        // query for disabling the constraint for updating forigne key(diagnoseid) in patient
        String updateQuery1 = "SET foreign_key_checks = 0;";
        // query for updating forign key(diagnoseid) in patient table
        String updateQuery2 = "UPDATE patient SET diagnoseid = 0 where SSN = ?;";
        // qury for enabling constraint
        String updateQuery3 = "SET foreign_key_checks = 1;";

        PreparedStatement pst1 = connection.prepareStatement(updateQuery1);
        pst1.executeUpdate();

        PreparedStatement pstmt1 = connection.prepareStatement(updateQuery2);
        pstmt1.setString(1, SSN);
        pstmt1.executeUpdate();

        PreparedStatement pst3 = connection.prepareStatement(updateQuery3);
        pst3.executeUpdate();

        // query for deleting the diagnose from diagnose table
        String deleteQuery = "Delete from diagnos WHERE idDiagnos = ? ";
        PreparedStatement pstmt2 = connection.prepareStatement(deleteQuery);
        pstmt2.setInt(1, id);
        pstmt2.executeUpdate();
    }

}
