package com.example.healthy_wallet;

import java.util.HashMap;
import java.util.Map;

public class ExpenseCategory extends AbstractCategory{
    private static final Map<String, Integer> categoryCount = new HashMap<>();
    private static int totalCount = 0;

    public ExpenseCategory(String categoryName) {
        super(categoryName);
        if (!getCategory().equals("No category")) {
            categoryCount.put(categoryName, categoryCount.getOrDefault(categoryName, 0) + 1);
            totalCount++;
        }
    }

    @Override
    public double getPercentageOfCategory() {
        return 100.0 * categoryCount.get(this.categoryName) / totalCount;
    }

    public static Map<String, Double> getPercentagesOfAllCategories() {
        return calculatePercentagesOfAllCategories(categoryCount, totalCount);
    }
}
