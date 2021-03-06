package sample.databaseConnection;

import java.sql.*;

public class Connect {

private Connection conn;
public static final String DB_NAME = "nursinghome";
public static final String CONNECTION_URL = "jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome?useSSL=false";
public static final String PASSWORD = "Vw3J!60l-0kd";

private static Connect instance = new Connect();
private Connect(){
}
  public static Connect getInstance(){
    return instance;
    }


    public Boolean open(){

        try {
            conn = DriverManager.getConnection(CONNECTION_URL,DB_NAME,PASSWORD);
            Statement statement = conn.createStatement();

        return true;

    } catch (SQLException e) {
        System.out.println("Could not connect to a database" + e.getMessage());
        return false;
    }
    }

    public void close(){
        try {
            if (conn!= null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close connection" + e.getMessage());
        }

    }
    public Connection getConn() {
        return conn;
    }
}
