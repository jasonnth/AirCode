package com.nytimes.android.external.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public abstract class ForwardingCache<K, V> extends ForwardingObject implements Cache<K, V> {
    /* access modifiers changed from: protected */
    public abstract Cache<K, V> delegate();

    protected ForwardingCache() {
    }

    public V getIfPresent(Object key) {
        return delegate().getIfPresent(key);
    }

    public V get(K key, Callable<? extends V> valueLoader) throws ExecutionException {
        return delegate().get(key, valueLoader);
    }

    public void put(K key, V value) {
        delegate().put(key, value);
    }

    public void invalidate(Object key) {
        delegate().invalidate(key);
    }
}
