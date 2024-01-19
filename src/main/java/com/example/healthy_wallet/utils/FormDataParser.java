package com.example.healthy_wallet.utils;

import com.example.healthy_wallet.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class FormDataParser {

    public static AbstractTransaction parseAndSanitizeTransactionData(String type, String amountStr, String dateStr, String description, String category, String priorityStr) throws InvalidDateException {
        double amount = parseAmount(amountStr);
        LocalDate date = parseDate(dateStr);
        description = sanitizeString(description, "No description");
        category = sanitizeString(category, "No category");
        TransactionPriority priority = TransactionPriority.fromString(priorityStr);

        if (type.equals("Income")) {
            return new Income(
                    amount,
                    date,
                    description,
                    category);
        } else {
            return new Expense(
                    amount,
                    date,
                    description,
                    category,
                    priority);
        }
    }

    private static double parseAmount(String amountStr) throws IllegalArgumentException {
        try {
            double amount = Double.parseDouble(amountStr);

            if (amount < 0) {
                throw new IllegalArgumentException("Amount cannot be negative.");
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount. Please only use digits.");
        }
    }

    private static LocalDate parseDate(String dateString) throws InvalidDateException {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date format. Please enter a date in the format YYYY-MM-DD.");
        }
    }

    private static String sanitizeString(String str, String defaultValue) {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }
        // Remove special characters, if necessary
        str = str.replaceAll("[^\\w\\s()@]", "");
        return str;
    }
}
