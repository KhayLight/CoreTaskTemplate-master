package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionURL = "jdbc:mysql://localhost:3306/mydbtest";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionURL, userName,
                    password);
            /*if(!conn.isClosed()){
                System.out.println("Connection to the database is successful");
            }*/
        } catch (SQLException e) {
            System.err.println("Failed to load driver class");
        }
        return conn;
    }
}

