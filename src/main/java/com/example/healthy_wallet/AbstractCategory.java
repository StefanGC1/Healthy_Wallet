package com.example.healthy_wallet;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCategory implements ICategory{
    protected final String categoryName;

    public AbstractCategory(String categoryName)
    {
        this.categoryName = (categoryName == null || categoryName.isEmpty()) ? "No category" : categoryName;
    }

    @Override
    public String getCategory()
    {
        return categoryName;
    }

    protected static Map<String, Double> calculatePercentagesOfAllCategories(Map<String, Integer> categoryCount, Integer totalCount)
    {
        Map<String, Double> percentages = new HashMap<>();
        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            percentages.put(entry.getKey(), 100.0 * entry.getValue() / totalCount);
        }
        return percentages;
    }
}
