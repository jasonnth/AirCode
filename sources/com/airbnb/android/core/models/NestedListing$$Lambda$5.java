package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class NestedListing$$Lambda$5 implements Predicate {
    private final long arg$1;

    private NestedListing$$Lambda$5(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new NestedListing$$Lambda$5(j);
    }

    public boolean apply(Object obj) {
        return NestedListing.isPossibleParentChild(this.arg$1, (NestedListing) obj);
    }
}
