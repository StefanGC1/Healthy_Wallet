package com.example.healthy_wallet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ListView<String> transactionListView;
    private List<AbstractTransaction> transactions = new ArrayList<>();

    @FXML
    public void initialize()
    {
        // Dummy data for testing, not present

        updateTransactionListView();
    }

    @FXML
    private void updateTransactionListView()
    {
        transactionListView.getItems().clear();
        for (AbstractTransaction transaction : transactions) {
            transactionListView.getItems().add(transaction.getType() + " - "
                    + transaction.getCategory() + " - " + transaction.getDescription() + " - "
                    + transaction.getDate() + " - "  + transaction.getAmount() + "$");
        }
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Add");
    }

    @FXML
    protected void onAddTransaction() { // Test function with dummy data
        Expense expense = new Expense(50.0, LocalDate.of(2000, 1, 1), "", "aa");
        transactions.add(expense);

        System.out.println(expense.getCategory() + " with percentage " + expense.getPercentageOfCategoryName());

        updateTransactionListView();
    }
}