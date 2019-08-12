package com.airbnb.android.lib.utils.listing;

import com.airbnb.android.core.models.Listing;

final /* synthetic */ class ListingProgressUtil$$Lambda$4 implements RequiredStep {
    private static final ListingProgressUtil$$Lambda$4 instance = new ListingProgressUtil$$Lambda$4();

    private ListingProgressUtil$$Lambda$4() {
    }

    public static RequiredStep lambdaFactory$() {
        return instance;
    }

    public boolean completed(Listing listing) {
        return listing.getHasSetLocation();
    }
}
