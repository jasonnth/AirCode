package com.nytimes.android.external.cache;

public interface Function<F, T> {
    T apply(F f);
}
