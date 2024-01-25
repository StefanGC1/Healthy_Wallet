package com.example.healthy_wallet;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private static volatile Account instance = null;
    private double balance;
    private int userID;
    private String username;
    private List<AbstractTransaction> transactions;

    public Account() {
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        instance = this;
    }
	
	public static Account getInstance() {
        if (instance == null) {
            instance = new Account();
        }
        return instance;
    }

    public static void clearInstance() {
        instance = null;
    }

    public void setTransactions(List<AbstractTransaction> transactions) {
        for (AbstractTransaction transaction: transactions)
            addTransaction(transaction);
    }

    public void addTransaction(AbstractTransaction transaction) {
        transactions.add(transaction);
        if (transaction instanceof Income) {
            balance += transaction.getAmount();
        } else if (transaction instanceof Expense) {
            balance -= transaction.getAmount();
        }
        // Additional logic can be added here for different types of transactions
    }

    public void removeTransaction(int index) {
        AbstractTransaction removedTransaction = transactions.remove(index);

        if (removedTransaction instanceof Income) {
            balance -= removedTransaction.getAmount();
        } else if (removedTransaction instanceof Expense) {
            balance += removedTransaction.getAmount();
        }
    }

    public void updateTransaction(AbstractTransaction original, AbstractTransaction updated) {
        int index = transactions.indexOf(original);
        if (index != -1) {
            transactions.set(index, updated);
            // Recalculate the balance by removing the original and adding the updated transaction
            if (original instanceof Income) {
                balance -= original.getAmount();
            } else if (original instanceof Expense) {
                balance += original.getAmount();
            }

            if (updated instanceof Income) {
                balance += updated.getAmount();
            } else if (updated instanceof Expense) {
                balance -= updated.getAmount();
            }
        }
    }

    public double getBalance() {
        return balance;
    }
    public void setUserID(int _userID) { this.userID = _userID; }
    public int getUserID() { return userID; }
    public void setUsername(String _username) { this.username = _username; }
    public String getUsername() { return username; }

    public List<AbstractTransaction> getTransactions() { return transactions; }
    // Additional utility methods as needed
}
