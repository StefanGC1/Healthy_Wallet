package com.example.healthy_wallet.utils;

import com.example.healthy_wallet.*;
import com.example.healthy_wallet.database.DatabaseConnector;
import com.example.healthy_wallet.ui.admin_scene_classes.UserInfo;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static int fetchNewUserId(String username) throws SQLException {
        PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                "SELECT user_id FROM users WHERE username = ?");
        stmt.setString(1, username);

        ResultSet rs = stmt.executeQuery();
        if (rs.next())
            return rs.getInt("user_id");
        else
            return -1;
    }

    public static void fetchTransactionsFromDatabase(Account account) {
        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT * FROM transactions WHERE user_id = ?");
            stmt.setInt(1, account.getUserID());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AbstractTransaction transaction = createTransactionFromResult(rs);
                account.addTransaction(transaction);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<AbstractTransaction> fetchTransactionsFromDatabaseForID(int userID) {
        List<AbstractTransaction> fetchedTransactions = new ArrayList<>();

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT * FROM transactions WHERE user_id = ?");
            stmt.setInt(1, userID);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                AbstractTransaction transaction = createTransactionFromResult(rs);
                fetchedTransactions.add(transaction);
            }

            return fetchedTransactions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<UserInfo> fetchUserInfoFromDatabase() {
        List<UserInfo> fetchedUserInfo = new ArrayList<>();

        try {
            PreparedStatement stmt = DatabaseConnector.getConnection().prepareStatement(
                    "SELECT users.username, users.user_id, COUNT(transactions.transaction_id) AS transaction_count " +
                    " FROM transactions " +
                    " JOIN users ON transactions.user_id = users.user_id " +
                    " WHERE transactions.timestamp > NOW() - INTERVAL 1 DAY " +
                    " GROUP BY users.user_id; ");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UserInfo userInfo = new UserInfo(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3)
                );

                fetchedUserInfo.add(userInfo);
            }
            return fetchedUserInfo;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static AbstractTransaction createTransactionFromResult(ResultSet rs) throws SQLException {
        AbstractTransaction transaction;
        if (rs.getString("type").equals("Income")) {
            transaction = new Income(
                    rs.getDouble("amount"),
                    LocalDate.parse(rs.getString("date")),
                    rs.getString("category"),
                    rs.getString("description"));
        } else {
            transaction = new Expense(
                    rs.getDouble("amount"),
                    LocalDate.parse(rs.getString("date")),
                    rs.getString("category"),
                    rs.getString("description"),
                    TransactionPriority.fromString(rs.getString("priority")));
        }

        return transaction;
    }

    public static void addTransactionToDatabase(AbstractTransaction transaction){
        try {
            Connection conn = DatabaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO transactions (user_id, type, amount, date, description, category, priority) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            stmt.setInt(1, Account.getInstance().getUserID());
            stmt.setString(2, transaction.getType()); // You'll need to determine how to get the type
            stmt.setDouble(3, transaction.getAmount());
            stmt.setDate(4, java.sql.Date.valueOf(transaction.getDate()));
            stmt.setString(5, transaction.getDescription());
            stmt.setString(6, transaction.getCategory());
            stmt.setString(7, transaction.getPriority().toString());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Transaction added successfully.");
            } else {
                System.out.println("Failed to add the transaction.");
            }
        } catch (SQLException e) {
            showErrorAlert(e.getMessage());
        }
    }
}
