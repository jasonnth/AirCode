package com.nytimes.android.external.cache;

import com.nytimes.android.external.cache.AbstractFuture.TrustedFuture;

public final class SettableFuture<V> extends TrustedFuture<V> {
    public static <V> SettableFuture<V> create() {
        return new SettableFuture<>();
    }

    private SettableFuture() {
    }

    public boolean set(V value) {
        return super.set(value);
    }

    public boolean setException(Throwable throwable) {
        return super.setException(throwable);
    }
}
