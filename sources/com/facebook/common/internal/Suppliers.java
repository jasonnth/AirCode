package com.facebook.common.internal;

public class Suppliers {
    /* renamed from: of */
    public static <T> Supplier<T> m1786of(final T instance) {
        return new Supplier<T>() {
            public T get() {
                return instance;
            }
        };
    }
}
