package com.example.healthy_wallet.database;

import java.net.ConnectException;
import java.sql.*;

public class DatabaseConnector {
    private final String url = "jdbc:mysql://localhost:3306/healthy_wallet"; // Replace with your database name
    private final String user = "appaccess"; // Replace with your MySQL username
    private final String password = "ddG#26"; // Replace with your MySQL password
    private static Connection dbConnection = null;

    public boolean connect() {
        try {
            dbConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the MySQL server successfully.");
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            return false;
        }
    }

    // Method to close the connection
    public void closeConnection() {
        if (dbConnection != null) {
            try {
                dbConnection.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("SQLException: " + e.getMessage());
            }
        }
    }

   public void testConnection() {
        try {
            this.connect();
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM healthy_wallet.test_table");

            while (rs.next()) {
                System.out.println(rs.getString(1) + " || " + rs.getString(2));
            }
            this.closeConnection();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
   }

   public static Connection getConnection() { return dbConnection; }
}
