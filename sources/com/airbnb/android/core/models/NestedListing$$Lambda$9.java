package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class NestedListing$$Lambda$9 implements Comparator {
    private static final NestedListing$$Lambda$9 instance = new NestedListing$$Lambda$9();

    private NestedListing$$Lambda$9() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((NestedListing) obj).getName().compareTo(((NestedListing) obj2).getName());
    }
}
