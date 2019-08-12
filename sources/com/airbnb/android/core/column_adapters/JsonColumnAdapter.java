package com.airbnb.android.core.column_adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.squareup.sqldelight.ColumnAdapter;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import java.io.IOException;
import java.lang.reflect.Type;

public class JsonColumnAdapter<T> implements ColumnAdapter<T, byte[]> {
    private final Lazy<ObjectReader> reader;
    private final Lazy<ObjectWriter> writer;

    public JsonColumnAdapter(Type type) {
        this(JsonColumnAdapter$$Lambda$1.lambdaFactory$(), type);
    }

    public JsonColumnAdapter(Lazy<ObjectMapper> objectMapper, Type type) {
        this.reader = DoubleCheck.lazy(JsonColumnAdapter$$Lambda$2.lambdaFactory$(objectMapper, type));
        this.writer = DoubleCheck.lazy(JsonColumnAdapter$$Lambda$3.lambdaFactory$(objectMapper, type));
    }

    public T decode(byte[] databaseValue) {
        try {
            return ((ObjectReader) this.reader.get()).readValue(databaseValue);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] encode(T value) {
        try {
            return ((ObjectWriter) this.writer.get()).writeValueAsBytes(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
