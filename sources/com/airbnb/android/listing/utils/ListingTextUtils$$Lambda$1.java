package com.airbnb.android.listing.utils;

import com.airbnb.android.core.models.CheckInInformation;
import com.google.common.base.Predicate;

final /* synthetic */ class ListingTextUtils$$Lambda$1 implements Predicate {
    private static final ListingTextUtils$$Lambda$1 instance = new ListingTextUtils$$Lambda$1();

    private ListingTextUtils$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((CheckInInformation) obj).isIsOptionAvailable().booleanValue();
    }
}
