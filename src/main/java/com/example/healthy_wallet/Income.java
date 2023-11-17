package com.example.healthy_wallet;

import java.time.LocalDate;

public class Income extends AbstractTransaction {
    public Income(double amount, LocalDate date, String description, String categoryName) {
        super(amount, date, description);
        this.category = new IncomeCategory(categoryName);
        this.type = "Income";
    }

    @Override
    public String getType() { return type; }

    public double getPercentageOfCategoryName() {
        return category.getPercentageOfCategory();
    }
}
