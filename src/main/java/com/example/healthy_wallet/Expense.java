package com.example.healthy_wallet;

import java.time.LocalDate;

public class Expense extends AbstractTransaction {
    public Expense(double amount, LocalDate date, String description, String categoryName, TransactionPriority priority) {
        super(amount, date, description, priority);
        this.category = new ExpenseCategory(categoryName);
        this.type = "Expense";
    }

    @Override
    public String getType() { return type; }

    public double getPercentageOfCategoryName() { return category.getPercentageOfCategory(); }
}
