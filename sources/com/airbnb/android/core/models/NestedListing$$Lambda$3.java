package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class NestedListing$$Lambda$3 implements Comparator {
    private final String arg$1;
    private final Comparator arg$2;

    private NestedListing$$Lambda$3(String str, Comparator comparator) {
        this.arg$1 = str;
        this.arg$2 = comparator;
    }

    public static Comparator lambdaFactory$(String str, Comparator comparator) {
        return new NestedListing$$Lambda$3(str, comparator);
    }

    public int compare(Object obj, Object obj2) {
        return NestedListing.lambda$getComparatorByZipTypeName$4(this.arg$1, this.arg$2, (NestedListing) obj, (NestedListing) obj2);
    }
}
