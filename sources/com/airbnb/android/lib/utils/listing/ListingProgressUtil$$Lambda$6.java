package com.airbnb.android.lib.utils.listing;

import com.airbnb.android.core.models.Listing;

final /* synthetic */ class ListingProgressUtil$$Lambda$6 implements RequiredStep {
    private static final ListingProgressUtil$$Lambda$6 instance = new ListingProgressUtil$$Lambda$6();

    private ListingProgressUtil$$Lambda$6() {
    }

    public static RequiredStep lambdaFactory$() {
        return instance;
    }

    public boolean completed(Listing listing) {
        return listing.isInstantBookingVisibilitySet();
    }
}
