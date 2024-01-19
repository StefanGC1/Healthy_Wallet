package com.example.healthy_wallet;

public enum TransactionPriority {
    NONE,
    VERY_LOW,
    LOW,
    MEDIUM,
    HIGH,
    VERY_HIGH;

    public static TransactionPriority fromString(String value) {
        try {
            return TransactionPriority.valueOf(value.replace(" ", "_").toUpperCase());
        } catch (IllegalArgumentException e) {
            return null; // Handle invalid input
        }
    }
}
