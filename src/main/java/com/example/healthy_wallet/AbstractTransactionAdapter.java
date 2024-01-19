package com.example.healthy_wallet;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;

public class AbstractTransactionAdapter implements JsonSerializer<AbstractTransaction>, JsonDeserializer<AbstractTransaction> {
// Unsure if used
    @Override
    public JsonElement serialize(AbstractTransaction src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        String type = src.getType();
        result.add("type", new JsonPrimitive(src.getType()));
        result.add("amount", new JsonPrimitive(src.getAmount()));
        result.add("date", new JsonPrimitive(src.getDate().toString()));
        result.add("description", new JsonPrimitive(src.getDescription()));
        result.add("category", new JsonPrimitive(src.getCategory()));
        result.add("priority",new JsonPrimitive(src.getPriority().toString()));
        return result;
    }

    @Override
    public AbstractTransaction deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();
        double amount = jsonObject.get("amount").getAsDouble();
        LocalDate date = LocalDate.parse(jsonObject.get("date").getAsString());
        String description = jsonObject.get("description").getAsString();
        String category = jsonObject.getAsJsonObject("category").get("categoryName").getAsString();
        TransactionPriority priority = TransactionPriority.valueOf(jsonObject.get("priority").getAsString());

        AbstractTransaction transaction;
        if ("Income".equals(type)) {
            transaction = new Income(amount, date, description, category);
        } else if ("Expense".equals(type)) {
            transaction = new Expense(amount, date, description, category, priority);
        } else {
            throw new JsonParseException("Unknown element type: " + type);
        }

        return transaction;
    }
}