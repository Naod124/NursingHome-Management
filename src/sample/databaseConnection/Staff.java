package sample.databaseConnection;

import java.sql.*;

public class Staff extends Connect {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Staff() {
    }


    public void insertIntoPStaffTable(String firstName , String lastName ,String ssn , String dOb , String address , String email , double salary , String role) {
        try {

            Connection connect = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
            PreparedStatement statement = connect.prepareStatement("insert into staff (FirstName,LastName,SSN,Adress,DateOfbirth,Email,Salary,Role) values (?,?,?,?,?,?,?,?);");
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,ssn);
            statement.setString(4,dOb);
            statement.setString(5,address);
            statement.setString(6,email);
            statement.setString(7, String.valueOf(salary));
            statement.setString(8,role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
