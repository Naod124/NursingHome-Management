package sample.databaseConnection;

import java.sql.*;

public class MedcinQueries {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public MedcinQueries() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome",
                    "nursinghome", "Vw3J!60l-0kd");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertMedicine(String medicien,String SSN) throws SQLException{

        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");

        String updateQuery = "UPDATE patient SET medicien = '" + medicien + "' WHERE SSN = '" + SSN + "';";

        PreparedStatement pstmt = connection.prepareStatement(updateQuery);
        pstmt.executeUpdate();

    }

    public void removeMedicine(String SSN) throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");

        String deleteQuery = "UPDATE patient SET medicien = '' WHERE SSN = '" + SSN + "';";
        PreparedStatement pstmt = connection.prepareStatement(deleteQuery);
        pstmt.executeUpdate();
    }

    public void updateMedicine(String medicien,String SSN) throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome", "nursinghome",
                "Vw3J!60l-0kd");

        String updateQuery = "UPDATE patient SET medicien = '" + medicien + "' WHERE SSN = '" + SSN + "';";
        PreparedStatement pstmt = connection.prepareStatement(updateQuery);
        pstmt.executeUpdate();
    }
}
