package Databasesql;

import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databasesql {

    public Connection connection() {
        String url = "jdbc:mysql://localhost:3306/hospital";
        String username = "root";
        String password = "Maria200415";
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
