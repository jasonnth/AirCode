package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class ListingRegistrationProcessInputGroup$$Lambda$3 implements Predicate {
    private static final ListingRegistrationProcessInputGroup$$Lambda$3 instance = new ListingRegistrationProcessInputGroup$$Lambda$3();

    private ListingRegistrationProcessInputGroup$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ListingRegistrationProcessInputGroup.lambda$getAddressInput$2((ListingRegistrationQuestion) obj);
    }
}
