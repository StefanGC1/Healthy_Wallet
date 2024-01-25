package com.example.healthy_wallet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalendarManager {
    private Map<LocalDate, List<AbstractTransaction>> transactionsByDate;

    public CalendarManager() {
        transactionsByDate = new HashMap<>();
        populateTransactionMap();
    }

    private void populateTransactionMap() {
        List<AbstractTransaction> allTransactions = Account.getInstance().getTransactions();
        for (AbstractTransaction transaction : allTransactions) {
            LocalDate date = transaction.getDate(); // Assuming getDate() returns a DateTime object
            transactionsByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(transaction);
        }
    }

    public List<LocalDate> getAllTransactionDates() {
        return new ArrayList<>(transactionsByDate.keySet());
    }

    public List<AbstractTransaction> getTransactionsForDate(LocalDate date) {
        return transactionsByDate.getOrDefault(date, new ArrayList<>());
    }

    public List<AbstractTransaction> getTransactionsForMonth(int year, int month) {
        return transactionsByDate.entrySet().stream()
                .filter(entry -> isSameYearAndMonth(entry.getKey(), year, month))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }

    private boolean isSameYearAndMonth(LocalDate date, int year, int month) {
        return date.getYear() == year && date.getMonthValue() == month;
    }

    public List<AbstractTransaction> getTransactionsForPeriod(LocalDate start, LocalDate end) {
        return transactionsByDate.entrySet().stream()
                .filter(entry -> !entry.getKey().isBefore(start) && !entry.getKey().isAfter(end))
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toList());
    }
}
