package com.squareup.sqldelight;

public interface ColumnAdapter<T, S> {
    T decode(S s);

    S encode(T t);
}
