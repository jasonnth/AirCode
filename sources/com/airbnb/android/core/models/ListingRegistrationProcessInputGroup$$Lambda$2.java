package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class ListingRegistrationProcessInputGroup$$Lambda$2 implements Predicate {
    private static final ListingRegistrationProcessInputGroup$$Lambda$2 instance = new ListingRegistrationProcessInputGroup$$Lambda$2();

    private ListingRegistrationProcessInputGroup$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ListingRegistrationProcessInputGroup.lambda$hasAddressInput$1((ListingRegistrationQuestion) obj);
    }
}
