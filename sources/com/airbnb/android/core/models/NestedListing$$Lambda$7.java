package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class NestedListing$$Lambda$7 implements Predicate {
    private static final NestedListing$$Lambda$7 instance = new NestedListing$$Lambda$7();

    private NestedListing$$Lambda$7() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((NestedListing) obj).isUnlinked();
    }
}
