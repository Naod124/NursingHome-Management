package sample.databaseConnection;

import java.sql.*;

public class StaffQueries {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public StaffQueries() {
    }


    public void insertIntoPStaffTable(String firstName , String lastName , String ssn , String address, String dOb , String email , double salary , String role, String userName , String password) {
        try {

            connection = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
            PreparedStatement statement = connection.prepareStatement("insert into staff (FirstName,LastName,SSN,Adress,DateOfbirth,Email,Salary,Role) values (?,?,?,?,?,?,?,?);");
            PreparedStatement statement1 = connection.prepareStatement("insert into login (Username,Password,staff_SSN) values (?,?,?);");
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,ssn);
            statement.setString(4,address);
            statement.setString(5, dOb);
            statement.setString(6,email);
            statement.setString(7, String.valueOf(salary));
            statement.setString(8,role);
            statement1.setString(1,userName);
            statement1.setString(2,password);
            statement1.setString(3,ssn);
            statement.execute();
            statement.close();
            statement1.execute();
            statement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateIntoStaffTable() {

    }
    public void viewAllStaffTable() {
        try {
            connection = DriverManager.getConnection(Connect.CONNECTION_URL, Connect.DB_NAME, Connect.PASSWORD);
            PreparedStatement statement = connection.prepareStatement("select * from staff;");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
