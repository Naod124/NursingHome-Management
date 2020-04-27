package sample.databaseConnection;

import java.sql.*;

public class Connect {

private Connection conn;
public static final String DB_NAME = "nursinghome";
public static final String CONNECTION_URL = "jdbc:mysql://den1.mysql3.gear.host:3306/nursinghome";
public static final String PASSWORD = "Vw3J!60l-0kd";

    public Connection getConn() {
        return conn;
    }

    public Boolean open(){

        System.out.println("Connecting database...");
    try {
        conn = DriverManager.getConnection(CONNECTION_URL,DB_NAME,PASSWORD);
        System.out.println("Database connected!");
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
}
