package com.pulpomatic.pulpomatic.util.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by Nicol Acosta on 9/20/16.
 * nicol@parkiller.com
 */
public class MapEntryDeserializer implements JsonDeserializer<Map.Entry<String, Object>> ,JsonSerializer<Map.Entry<String, Object>> {

    private static final String TAG = MapEntryDeserializer.class.getSimpleName();

    private static final String ID = "id";
    private static final String CODE = "code";

    @Override
    public Map.Entry<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject data = json.getAsJsonObject();
        return new Map.Entry<String, Object>() {
            @Override
            public String getKey() {
                return data.getAsJsonPrimitive(ID).getAsString();
            }

            @Override
            public Object getValue() {
                return data.getAsJsonPrimitive(CODE).getAsString();
            }

            @Override
            public Object setValue(Object o) {
                throw new IllegalAccessError("you can not change the value");
            }
        };
    }

    @Override
    public JsonElement serialize(Map.Entry<String, Object> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.add(ID, new JsonPrimitive(src.getKey()));
        json.add(CODE, new JsonPrimitive(src.getValue().toString()));
        return json;
    }
}
