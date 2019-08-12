package com.apollographql.apollo.internal.cache.normalized;

public interface Transaction<T, R> {
    R execute(T t);
}
