package com.example.healthy_wallet;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

class ParsedData {
    double amount;
    LocalDate date;
    String description;
    String category;

    public ParsedData(double amount, LocalDate date, String description, String category) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.category = category;
    }
}

public class FormDataParser {

    public static ParsedData parseAndSanitize(String amountStr, String dateStr, String description, String category) throws InvalidDateException {
        double amount = parseAmount(amountStr);
        LocalDate date = parseDate(dateStr);
        description = sanitizeString(description, "No description");
        category = sanitizeString(category, "No category");

        return new ParsedData(amount, date, description, category);
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
