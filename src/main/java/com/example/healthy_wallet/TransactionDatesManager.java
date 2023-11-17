package com.example.healthy_wallet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionDatesManager {
    private Map<LocalDate, List<AbstractTransaction>> transactionsByDate;

    public TransactionDatesManager() {
        transactionsByDate = new HashMap<>();
    }

    public void addTransaction(AbstractTransaction transaction) {
        transactionsByDate.computeIfAbsent(transaction.getDate(), k -> new ArrayList<>()).add(transaction);
    }

    public List<AbstractTransaction> getTransactionsByDate(LocalDate date) {
        return transactionsByDate.getOrDefault(date, new ArrayList<>());
    }

    public Map<LocalDate, List<AbstractTransaction>> getAllTransactions() {
        return transactionsByDate;
    }

    // Additional methods for date navigation can be added here
}
