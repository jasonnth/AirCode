package com.nytimes.android.external.cache;

public interface Weigher<K, V> {
    int weigh(K k, V v);
}
