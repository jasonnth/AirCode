package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class NestedListing$$Lambda$4 implements Comparator {
    private final Comparator arg$1;

    private NestedListing$$Lambda$4(Comparator comparator) {
        this.arg$1 = comparator;
    }

    public static Comparator lambdaFactory$(Comparator comparator) {
        return new NestedListing$$Lambda$4(comparator);
    }

    public int compare(Object obj, Object obj2) {
        return NestedListing.lambda$getComparatorByStatusZipTypeName$5(this.arg$1, (NestedListing) obj, (NestedListing) obj2);
    }
}
