package com.example.healthy_wallet;

import java.time.LocalDate;

public abstract class AbstractTransaction implements ITransaction {
    protected String type;
    protected double amount;
    protected LocalDate date;
    protected String description;
    protected AbstractCategory category;
    protected TransactionPriority priority;

    // Constructor
    public AbstractTransaction(double amount, LocalDate date, String description, TransactionPriority priority) {
        this.amount = amount;
        this.date = date;
        this.description = (description == null || description.isEmpty()) ? "No description" : description;
        this.priority = priority;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCategory() { return category.getCategory(); }

    public TransactionPriority getPriority() { return priority; }
}
