package com.google.gson.jpush;

public enum LongSerializationPolicy {
    DEFAULT {
        public JsonElement serialize(Long value) {
            return new JsonPrimitive((Number) value);
        }
    },
    STRING {
        public JsonElement serialize(Long value) {
            return new JsonPrimitive(String.valueOf(value));
        }
    };

    public abstract JsonElement serialize(Long l);
}
