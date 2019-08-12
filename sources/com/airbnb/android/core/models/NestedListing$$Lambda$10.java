package com.airbnb.android.core.models;

import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.utils.Check;
import java.util.Comparator;

final /* synthetic */ class NestedListing$$Lambda$10 implements Comparator {
    private static final NestedListing$$Lambda$10 instance = new NestedListing$$Lambda$10();

    private NestedListing$$Lambda$10() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((SpaceType) Check.notNull(((NestedListing) obj).getSpaceType())).compareTo(((NestedListing) obj2).getSpaceType());
    }
}
