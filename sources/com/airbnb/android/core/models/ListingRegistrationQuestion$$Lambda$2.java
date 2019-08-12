package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class ListingRegistrationQuestion$$Lambda$2 implements Predicate {
    private final String arg$1;

    private ListingRegistrationQuestion$$Lambda$2(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new ListingRegistrationQuestion$$Lambda$2(str);
    }

    public boolean apply(Object obj) {
        return ((ListingRegistrationAnswer) obj).getValue().equalsIgnoreCase(this.arg$1);
    }
}
