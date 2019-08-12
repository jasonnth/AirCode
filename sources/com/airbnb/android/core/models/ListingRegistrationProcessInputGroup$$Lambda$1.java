package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class ListingRegistrationProcessInputGroup$$Lambda$1 implements Predicate {
    private final String arg$1;

    private ListingRegistrationProcessInputGroup$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new ListingRegistrationProcessInputGroup$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((ListingRegistrationQuestion) obj).getInputKey().equalsIgnoreCase(this.arg$1);
    }
}
