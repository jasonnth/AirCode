package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class NestedListing$$Lambda$6 implements Predicate {
    private static final NestedListing$$Lambda$6 instance = new NestedListing$$Lambda$6();

    private NestedListing$$Lambda$6() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((NestedListing) obj).isParent();
    }
}
