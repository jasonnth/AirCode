package com.airbnb.android.aireventlogger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;

final class JacksonConverter<T> implements Converter<T> {
    private final ObjectWriter writer;

    JacksonConverter(ObjectWriter writer2) {
        this.writer = writer2;
    }

    public byte[] toJson(T value) {
        try {
            return this.writer.writeValueAsBytes(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
