package com.apollographql.apollo.api.internal;

public interface Function<T, R> {
    R apply(T t);
}
