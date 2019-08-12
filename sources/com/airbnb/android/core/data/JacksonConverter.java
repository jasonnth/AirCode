package com.airbnb.android.core.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;

public final class JacksonConverter<T> implements Converter<T> {
    private final ObjectReader reader;
    private final ObjectWriter writer;

    public JacksonConverter(ObjectReader reader2, ObjectWriter writer2) {
        this.reader = reader2;
        this.writer = writer2;
    }

    public byte[] toJson(T value) {
        try {
            return this.writer.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public T fromJson(byte[] data) {
        try {
            return this.reader.readValue(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public T fromJson(JsonNode node) {
        try {
            return this.reader.readValue(node);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
