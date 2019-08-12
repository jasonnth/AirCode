package com.airbnb.android.lib.utils.listing;

import com.airbnb.android.core.models.Listing;

final /* synthetic */ class ListingProgressUtil$$Lambda$2 implements RequiredStep {
    private static final ListingProgressUtil$$Lambda$2 instance = new ListingProgressUtil$$Lambda$2();

    private ListingProgressUtil$$Lambda$2() {
    }

    public static RequiredStep lambdaFactory$() {
        return instance;
    }

    public boolean completed(Listing listing) {
        return listing.getHouseRulesVisited();
    }
}
