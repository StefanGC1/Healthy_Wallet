package com.example.healthy_wallet;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class Account {
    private TransactionDatesManager dateManager;
    private CalendarManager calendarManager;
    private double balance;

    public Account() {
        dateManager = new TransactionDatesManager();
        calendarManager = new CalendarManager();
        balance = 0.0;
    }

    public void addTransaction(AbstractTransaction transaction) {
        dateManager.addTransaction(transaction);
        updateBalance(transaction);
        updateCalendarRange(transaction);
    }

    private void updateBalance(AbstractTransaction transaction) {
        if (transaction instanceof Income) {
            balance += transaction.getAmount();
        } else if (transaction instanceof Expense) {
            balance -= transaction.getAmount();
        }
    }

    public double getBalance() {
        return balance;
    }

    public List<AbstractTransaction> getTransactionsByDate(LocalDate date) {
        return dateManager.getTransactionsByDate(date);
    }

    private void updateCalendarRange(AbstractTransaction transaction) {
        LocalDate transactionDate = transaction.getDate();
        // TODO: Update startDate and endDate of calendarManager if some conditions are met
    }

    public List<LocalDate> getDatesOfMonth(YearMonth yearMonth) {
        return calendarManager.getDatesOfMonth(yearMonth);
    }

    public List<LocalDate> getDatesOfYear(int year) {
        return calendarManager.getDatesOfYear(year);
    }
}
