package com.airbnb.android.aireventlogger;

import java.lang.reflect.Type;

public interface Converter<T> {

    public interface Factory {
        Converter<?> get(Type type);
    }

    byte[] toJson(T t);
}
