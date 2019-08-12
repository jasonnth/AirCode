package com.apollographql.apollo.api.internal;

import java.io.Serializable;

public abstract class Optional<T> implements Serializable {
    public abstract T get();

    public abstract boolean isPresent();

    public abstract T orNull();

    public abstract <V> Optional<V> transform(Function<? super T, V> function);

    public static <T> Optional<T> absent() {
        return Absent.withType();
    }

    /* renamed from: of */
    public static <T> Optional<T> m1700of(T reference) {
        return new Present(Utils.checkNotNull(reference));
    }

    public static <T> Optional<T> fromNullable(T nullableReference) {
        return nullableReference == null ? absent() : new Present(nullableReference);
    }

    Optional() {
    }
}
