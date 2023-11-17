package com.example.healthy_wallet;

import java.util.HashMap;
import java.util.Map;

public class IncomeCategory extends AbstractCategory{
    private static final Map<String, Integer> categoryCount = new HashMap<>();
    private static Integer totalCount = 0;

    public IncomeCategory(String categoryName) {
        super(categoryName);
        if (!this.getCategory().equals("No category")) {
            categoryCount.put(categoryName, categoryCount.getOrDefault(categoryName, 0) + 1);
            totalCount++;
        }
    }

    @Override
    public double getPercentageOfCategory() {
        return 100.0 * categoryCount.get(getCategory()) / totalCount;
    }

    public static Map<String, Double> getPercentagesOfAllCategories() {
        return calculatePercentagesOfAllCategories(categoryCount, totalCount);
    }
}
