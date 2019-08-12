package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class Listing$$Lambda$4 implements Predicate {
    private final ListingPersonaInput arg$1;

    private Listing$$Lambda$4(ListingPersonaInput listingPersonaInput) {
        this.arg$1 = listingPersonaInput;
    }

    public static Predicate lambdaFactory$(ListingPersonaInput listingPersonaInput) {
        return new Listing$$Lambda$4(listingPersonaInput);
    }

    public boolean apply(Object obj) {
        return Listing.lambda$setPersonaAnswer$1(this.arg$1, (ListingPersonaInput) obj);
    }
}
