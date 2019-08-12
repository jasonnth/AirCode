package com.airbnb.android.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public final class JacksonUtils {
    private JacksonUtils() {
    }

    public static ObjectWriter writerForType(ObjectMapper mapper, Type type) {
        return mapper.writerFor(mapper.getTypeFactory().constructType(type));
    }

    public static ObjectReader readerForType(ObjectMapper mapper, Type type) {
        return mapper.readerFor(mapper.getTypeFactory().constructType(type));
    }

    public static <T> List<T> readJsonArray(ObjectMapper objectMapper, String value) {
        try {
            return (List) readerForType(objectMapper, List.class).readValue(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String writeJsonArray(ObjectMapper objectMapper, List<T> value) {
        try {
            return writerForType(objectMapper, List.class).writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
