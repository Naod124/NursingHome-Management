package sample.databaseConnection;

import java.sql.*;

public abstract class Connect {

private Connection conn;


    protected Connect(){
    }

    public Connection getConn() {
        return conn;
    }
}
