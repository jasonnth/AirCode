package com.nytimes.android.external.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public interface Cache<K, V> {
    V get(K k, Callable<? extends V> callable) throws ExecutionException;

    V getIfPresent(Object obj);

    void invalidate(Object obj);

    void put(K k, V v);
}
