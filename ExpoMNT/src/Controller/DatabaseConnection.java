package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

//For login database

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "logindb";
        String databaseUser = "root";
        String databasePassword = "zephyrusm16";
        String url = "jdbc:mysql://localhost/" + databaseName;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
