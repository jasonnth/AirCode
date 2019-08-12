package com.apollographql.apollo;

public interface CustomTypeAdapter<T> {
    T decode(String str);
}
