package com.example.healthy_wallet;

import java.time.LocalDate;
import com.google.gson.*;
import java.lang.reflect.Type;

public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString()); // "yyyy-MM-dd"
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString()); // "yyyy-MM-dd"
    }
}
