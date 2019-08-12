package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Set;

final class ArrayJsonAdapter extends JsonAdapter<Object> {
    public static final Factory FACTORY = new Factory() {
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
            Type elementType = Types.arrayComponentType(type);
            if (elementType != null && annotations.isEmpty()) {
                return new ArrayJsonAdapter(Types.getRawType(elementType), moshi.adapter(elementType)).nullSafe();
            }
            return null;
        }
    };
    private final JsonAdapter<Object> elementAdapter;
    private final Class<?> elementClass;

    ArrayJsonAdapter(Class<?> elementClass2, JsonAdapter<Object> elementAdapter2) {
        this.elementClass = elementClass2;
        this.elementAdapter = elementAdapter2;
    }

    public void toJson(JsonWriter writer, Object value) throws IOException {
        writer.beginArray();
        int size = Array.getLength(value);
        for (int i = 0; i < size; i++) {
            this.elementAdapter.toJson(writer, Array.get(value, i));
        }
        writer.endArray();
    }

    public String toString() {
        return this.elementAdapter + ".array()";
    }
}
