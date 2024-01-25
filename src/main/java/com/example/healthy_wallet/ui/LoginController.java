package com.example.healthy_wallet.ui;

import com.example.healthy_wallet.Account;
import com.example.healthy_wallet.MainApplication;
import com.example.healthy_wallet.database.DatabaseConnector;
import com.example.healthy_wallet.utils.FormDataParser;
import com.example.healthy_wallet.utils.Utilities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    private MainApplication mainApp;

    public LoginController(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    public void handleLoginAction(String username, String password) {
        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT user_id, password, role FROM users WHERE username = ?");

            username = FormDataParser.sanitizeCredentialString(username);
            password = FormDataParser.sanitizeCredentialString(password);

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getString("password").equals(password)) {
                String role = rs.getString("role");

                if (role.equals("admin")) {
                    mainApp.showAdminSceneView();
                } else {
                    int _userID = Integer.parseInt(rs.getString("user_id"));

                    Account account = Account.getInstance();
                    account.setUserID(_userID);
                    account.setUsername(username);

                    Utilities.fetchTransactionsFromDatabase(account);
                    mainApp.showMainMenuView();
                }
            } else {
                Utilities.showErrorAlert("Wrong Login Info");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            Utilities.showErrorAlert(e.getMessage());
        }
    }

    public void handleRegisterAction(String username, String password) {
        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?, ?)");
            username = FormDataParser.sanitizeCredentialString(username);
            password = FormDataParser.sanitizeCredentialString(password);

            stmt.setString(1, username);
            stmt.setString(2, password);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                Account account = Account.getInstance();
                account.setUserID(Utilities.fetchNewUserId(username));
                mainApp.showMainMenuView();
            } else {
                Utilities.showErrorAlert("User registration failed.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            Utilities.showErrorAlert(e.getMessage());
        }
    }
}
