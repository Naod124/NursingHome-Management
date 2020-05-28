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


    public void insertIntoDiagnosTable(String diagnose, String SSN) throws SQLException {
        int insertid = 0;
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");

        String insertQuery = "INSERT INTO diagnos (DiagnosType) VALUES(?);";
        String idQuery = "select max(idDiagnos) from diagnos;";

        PreparedStatement pstmt = connection.prepareStatement(insertQuery);
        PreparedStatement pstmt1 = connection.prepareStatement(idQuery);

        pstmt.setString(1, diagnose);
        pstmt.executeUpdate();

        resultSet = pstmt1.executeQuery();
        while (resultSet.next()) {
            insertid = resultSet.getInt("max(idDiagnos)");
        }

        String updateQuery = "UPDATE patient SET diagnoseid = " + insertid + " WHERE SSN = '" + SSN + "';";
        PreparedStatement pstmt2 = connection.prepareStatement(updateQuery);
        pstmt2.executeUpdate();

    }

    public void updateIntoDiagnosTable(String diagnose, String SSN) throws SQLException {
        int id = 0;
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");
        String idQuery = "SELECT diagnoseid FROM patient where SSN = ?";
        PreparedStatement pstmt1 = connection.prepareStatement(idQuery);
        pstmt1.setString(1, SSN);

        resultSet = pstmt1.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("diagnoseid");
        }

        String insertQuery = "UPDATE diagnos SET DiagnosType = ? WHERE idDiagnos = ? ";

        PreparedStatement pstmt = connection.prepareStatement(insertQuery);

        pstmt.setString(1, diagnose);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
    }

    public ArrayList<DiagnoseTable> viewDignosTable() throws SQLException {
        ArrayList<DiagnoseTable> list = new ArrayList<>();
        String selectQuery = "SELECT * FROM patient LEFT OUTER JOIN diagnos ON patient.diagnoseid = diagnos.idDiagnos;";

        try {
            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
            resultSet = connection.createStatement().executeQuery(selectQuery);

            while (resultSet.next()) {
                DiagnoseTable diagnose = new DiagnoseTable("", "", "", "", "", "", "");
                diagnose.setDateOfBirth(resultSet.getString("DateOfBirth"));
                diagnose.setDiagnosis(resultSet.getString("DiagnosType"));
                diagnose.setFirstName(resultSet.getString("FirstName"));
                diagnose.setMedicien(resultSet.getString("medicien"));
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

    public void deleteIntoDiagnosTable(String SSN) throws SQLException {
        int id = 0;
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");
        String idQuery = "SELECT diagnoseid FROM patient where SSN = ?";
        PreparedStatement pstmt = connection.prepareStatement(idQuery);
        pstmt.setString(1, SSN);

        resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("diagnoseid");
        }

        String updateQuery1 = "SET foreign_key_checks = 0;";
        String updateQuery2 = "UPDATE patient SET diagnoseid = 0 where SSN = ?;";
        String updateQuery3 = "SET foreign_key_checks = 1;";

        PreparedStatement pst1 = connection.prepareStatement(updateQuery1);
        pst1.executeUpdate();

        PreparedStatement pstmt1 = connection.prepareStatement(updateQuery2);
        pstmt1.setString(1, SSN);
        pstmt1.executeUpdate();

        PreparedStatement pst3 = connection.prepareStatement(updateQuery3);
        pst3.executeUpdate();

        String deleteQuery = "Delete from diagnos WHERE idDiagnos = ? ";
        PreparedStatement pstmt2 = connection.prepareStatement(deleteQuery);
        pstmt2.setInt(1, id);
        pstmt2.executeUpdate();
    }

}
