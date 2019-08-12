package com.squareup.sqldelight;

import java.lang.Enum;

public final class EnumColumnAdapter<T extends Enum<T>> implements ColumnAdapter<T, String> {
    private final Class<T> cls;

    public static <T extends Enum<T>> EnumColumnAdapter<T> create(Class<T> cls2) {
        if (cls2 != null) {
            return new EnumColumnAdapter<>(cls2);
        }
        throw new NullPointerException("cls == null");
    }

    private EnumColumnAdapter(Class<T> cls2) {
        this.cls = cls2;
    }

    public T decode(String databaseValue) {
        return Enum.valueOf(this.cls, databaseValue);
    }

    public String encode(T value) {
        return value.name();
    }
}
