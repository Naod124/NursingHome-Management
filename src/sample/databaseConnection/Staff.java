package sample.databaseConnection;

import java.sql.*;

public class Staff extends Connect {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Staff() {
    }


    public void insertIntoPStaffTable() {
    }

    public void updateIntoStaffTable() {

    }
    public void viewStaffTable() {

    }
    public int verifyStaffLogin(String username, String password) { // The Log in verification method. Returns 1 incase found, else
        try {
            connection = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select `Username`, `Password` from `Log in` where Username = " + username + " AND " + password + ";  ");

        } catch (SQLException a) {

        }
        return 0;
    }
}
