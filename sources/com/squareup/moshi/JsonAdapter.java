package com.squareup.moshi;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import okio.Buffer;
import okio.BufferedSink;

public abstract class JsonAdapter<T> {

    public interface Factory {
        JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi);
    }

    public abstract void toJson(JsonWriter jsonWriter, T t) throws IOException;

    public final void toJson(BufferedSink sink, T value) throws IOException {
        toJson(JsonWriter.m2492of(sink), value);
    }

    public final String toJson(T value) {
        Buffer buffer = new Buffer();
        try {
            toJson((BufferedSink) buffer, value);
            return buffer.readUtf8();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final JsonAdapter<T> nullSafe() {
        return new JsonAdapter<T>() {
            public void toJson(JsonWriter writer, T value) throws IOException {
                if (value == null) {
                    writer.nullValue();
                } else {
                    this.toJson(writer, value);
                }
            }

            public String toString() {
                return this + ".nullSafe()";
            }
        };
    }
}
