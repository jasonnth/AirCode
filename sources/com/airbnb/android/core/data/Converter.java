package com.airbnb.android.core.data;

import com.fasterxml.jackson.databind.JsonNode;

public interface Converter<T> extends com.airbnb.android.aireventlogger.Converter<T> {
    T fromJson(JsonNode jsonNode);

    T fromJson(byte[] bArr);

    byte[] toJson(T t);
}
