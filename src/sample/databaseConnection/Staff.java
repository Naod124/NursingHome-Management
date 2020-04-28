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
            Connection connect = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
            PreparedStatement statement = connect.prepareStatement("select Username,Password FROM login where Username =? AND Password =?;");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            System.out.println();
            rs.next();
            return rs.getRow();
        } catch (SQLException a) {
            return 0;
        }
    }
}
