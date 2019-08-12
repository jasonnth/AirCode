package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import com.squareup.moshi.JsonReader.Options;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

final class StandardJsonAdapters {
    static final JsonAdapter<Boolean> BOOLEAN_JSON_ADAPTER = new JsonAdapter<Boolean>() {
        public void toJson(JsonWriter writer, Boolean value) throws IOException {
            writer.value(value.booleanValue());
        }

        public String toString() {
            return "JsonAdapter(Boolean)";
        }
    };
    static final JsonAdapter<Byte> BYTE_JSON_ADAPTER = new JsonAdapter<Byte>() {
        public void toJson(JsonWriter writer, Byte value) throws IOException {
            writer.value((long) (value.intValue() & 255));
        }

        public String toString() {
            return "JsonAdapter(Byte)";
        }
    };
    static final JsonAdapter<Character> CHARACTER_JSON_ADAPTER = new JsonAdapter<Character>() {
        public void toJson(JsonWriter writer, Character value) throws IOException {
            writer.value(value.toString());
        }

        public String toString() {
            return "JsonAdapter(Character)";
        }
    };
    static final JsonAdapter<Double> DOUBLE_JSON_ADAPTER = new JsonAdapter<Double>() {
        public void toJson(JsonWriter writer, Double value) throws IOException {
            writer.value(value.doubleValue());
        }

        public String toString() {
            return "JsonAdapter(Double)";
        }
    };
    public static final Factory FACTORY = new Factory() {
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
            if (!annotations.isEmpty()) {
                return null;
            }
            if (type == Boolean.TYPE) {
                return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER;
            }
            if (type == Byte.TYPE) {
                return StandardJsonAdapters.BYTE_JSON_ADAPTER;
            }
            if (type == Character.TYPE) {
                return StandardJsonAdapters.CHARACTER_JSON_ADAPTER;
            }
            if (type == Double.TYPE) {
                return StandardJsonAdapters.DOUBLE_JSON_ADAPTER;
            }
            if (type == Float.TYPE) {
                return StandardJsonAdapters.FLOAT_JSON_ADAPTER;
            }
            if (type == Integer.TYPE) {
                return StandardJsonAdapters.INTEGER_JSON_ADAPTER;
            }
            if (type == Long.TYPE) {
                return StandardJsonAdapters.LONG_JSON_ADAPTER;
            }
            if (type == Short.TYPE) {
                return StandardJsonAdapters.SHORT_JSON_ADAPTER;
            }
            if (type == Boolean.class) {
                return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER.nullSafe();
            }
            if (type == Byte.class) {
                return StandardJsonAdapters.BYTE_JSON_ADAPTER.nullSafe();
            }
            if (type == Character.class) {
                return StandardJsonAdapters.CHARACTER_JSON_ADAPTER.nullSafe();
            }
            if (type == Double.class) {
                return StandardJsonAdapters.DOUBLE_JSON_ADAPTER.nullSafe();
            }
            if (type == Float.class) {
                return StandardJsonAdapters.FLOAT_JSON_ADAPTER.nullSafe();
            }
            if (type == Integer.class) {
                return StandardJsonAdapters.INTEGER_JSON_ADAPTER.nullSafe();
            }
            if (type == Long.class) {
                return StandardJsonAdapters.LONG_JSON_ADAPTER.nullSafe();
            }
            if (type == Short.class) {
                return StandardJsonAdapters.SHORT_JSON_ADAPTER.nullSafe();
            }
            if (type == String.class) {
                return StandardJsonAdapters.STRING_JSON_ADAPTER.nullSafe();
            }
            if (type == Object.class) {
                return new ObjectJsonAdapter(moshi).nullSafe();
            }
            Class<?> rawType = Types.getRawType(type);
            if (rawType.isEnum()) {
                return new EnumJsonAdapter(rawType).nullSafe();
            }
            return null;
        }
    };
    static final JsonAdapter<Float> FLOAT_JSON_ADAPTER = new JsonAdapter<Float>() {
        public void toJson(JsonWriter writer, Float value) throws IOException {
            if (value == null) {
                throw new NullPointerException();
            }
            writer.value((Number) value);
        }

        public String toString() {
            return "JsonAdapter(Float)";
        }
    };
    static final JsonAdapter<Integer> INTEGER_JSON_ADAPTER = new JsonAdapter<Integer>() {
        public void toJson(JsonWriter writer, Integer value) throws IOException {
            writer.value((long) value.intValue());
        }

        public String toString() {
            return "JsonAdapter(Integer)";
        }
    };
    static final JsonAdapter<Long> LONG_JSON_ADAPTER = new JsonAdapter<Long>() {
        public void toJson(JsonWriter writer, Long value) throws IOException {
            writer.value(value.longValue());
        }

        public String toString() {
            return "JsonAdapter(Long)";
        }
    };
    static final JsonAdapter<Short> SHORT_JSON_ADAPTER = new JsonAdapter<Short>() {
        public void toJson(JsonWriter writer, Short value) throws IOException {
            writer.value((long) value.intValue());
        }

        public String toString() {
            return "JsonAdapter(Short)";
        }
    };
    static final JsonAdapter<String> STRING_JSON_ADAPTER = new JsonAdapter<String>() {
        public void toJson(JsonWriter writer, String value) throws IOException {
            writer.value(value);
        }

        public String toString() {
            return "JsonAdapter(String)";
        }
    };

    static final class EnumJsonAdapter<T extends Enum<T>> extends JsonAdapter<T> {
        private final T[] constants;
        private final Class<T> enumType;
        private final String[] nameStrings;
        private final Options options;

        public EnumJsonAdapter(Class<T> enumType2) {
            this.enumType = enumType2;
            try {
                this.constants = (Enum[]) enumType2.getEnumConstants();
                this.nameStrings = new String[this.constants.length];
                for (int i = 0; i < this.constants.length; i++) {
                    T constant = this.constants[i];
                    Json annotation = (Json) enumType2.getField(constant.name()).getAnnotation(Json.class);
                    this.nameStrings[i] = annotation != null ? annotation.name() : constant.name();
                }
                this.options = Options.m2491of(this.nameStrings);
            } catch (NoSuchFieldException e) {
                throw new AssertionError("Missing field in " + enumType2.getName(), e);
            }
        }

        public void toJson(JsonWriter writer, T value) throws IOException {
            writer.value(this.nameStrings[value.ordinal()]);
        }

        public String toString() {
            return "JsonAdapter(" + this.enumType.getName() + ")";
        }
    }

    static final class ObjectJsonAdapter extends JsonAdapter<Object> {
        private final Moshi moshi;

        public ObjectJsonAdapter(Moshi moshi2) {
            this.moshi = moshi2;
        }

        public void toJson(JsonWriter writer, Object value) throws IOException {
            Class<?> valueClass = value.getClass();
            if (valueClass == Object.class) {
                writer.beginObject();
                writer.endObject();
                return;
            }
            this.moshi.adapter(toJsonType(valueClass), Util.NO_ANNOTATIONS).toJson(writer, value);
        }

        private Class<?> toJsonType(Class<?> valueClass) {
            if (Map.class.isAssignableFrom(valueClass)) {
                return Map.class;
            }
            if (Collection.class.isAssignableFrom(valueClass)) {
                return Collection.class;
            }
            return valueClass;
        }

        public String toString() {
            return "JsonAdapter(Object)";
        }
    }
}
