package com.facebook.common.internal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ImmutableSet<E> extends HashSet<E> {
    private ImmutableSet(Set<E> set) {
        super(set);
    }

    public static <E> ImmutableSet<E> copyOf(Set<E> set) {
        return new ImmutableSet<>(set);
    }

    /* renamed from: of */
    public static <E> ImmutableSet<E> m1785of(E... elements) {
        HashSet<E> set = new HashSet<>();
        Collections.addAll(set, elements);
        return new ImmutableSet<>(set);
    }
}
