package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class NestedListing$$Lambda$1 implements Comparator {
    private final boolean arg$1;

    private NestedListing$$Lambda$1(boolean z) {
        this.arg$1 = z;
    }

    public static Comparator lambdaFactory$(boolean z) {
        return new NestedListing$$Lambda$1(z);
    }

    public int compare(Object obj, Object obj2) {
        return NestedListing.lambda$getComparatorByTypeAndName$2(this.arg$1, (NestedListing) obj, (NestedListing) obj2);
    }
}
