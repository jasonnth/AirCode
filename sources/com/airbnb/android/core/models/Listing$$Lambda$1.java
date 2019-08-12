package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class Listing$$Lambda$1 implements Predicate {
    private final Listing arg$1;

    private Listing$$Lambda$1(Listing listing) {
        this.arg$1 = listing;
    }

    public static Predicate lambdaFactory$(Listing listing) {
        return new Listing$$Lambda$1(listing);
    }

    public boolean apply(Object obj) {
        return Listing.lambda$getAdditionalHosts$0(this.arg$1, (User) obj);
    }
}
