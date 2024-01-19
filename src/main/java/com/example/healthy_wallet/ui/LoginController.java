package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.*;
import com.example.healthy_wallet.database.DatabaseConnector;
import com.example.healthy_wallet.utils.FileStorage;
import com.example.healthy_wallet.utils.Utilities;
import javafx.event.ActionEvent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class LoginController {

    private MainApplication mainApp;

    public LoginController(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void handleLoginAction(String username, String password) {
        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT user_id, password, role FROM users WHERE username = ?");
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getString("password").equals(password)) {
                String role = rs.getString("role");

                if (role.equals("admin")) {
                    mainApp.showAdminSceneView();
                } else {
                    int _userID = Integer.parseInt(rs.getString("user_id"));
                    System.out.println(_userID);

                    Account account = Account.getInstance();
                    account.setUserID(_userID);

                    Utilities.fetchTransactionsFromDatabase(account);
                    mainApp.showMainMenuView();
                }
            } else {
                System.out.println("Wrong Login Info");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void handleRegisterAction(String username, String password) {
        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?, ?)");
            stmt.setString(1, username);
            stmt.setString(2, password);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                Account account = Account.getInstance();
                account.setUserID(Utilities.fetchNewUserId(username));
//                FileStorage fileStorage = FileStorage.getInstance();
//                account.setTransactions(fileStorage.loadTransactions());
                mainApp.showMainMenuView();
            } else {
                System.out.println("User registration failed.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
