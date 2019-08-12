package com.airbnb.android.lib.utils.listing;

import com.airbnb.android.core.models.Listing;

final /* synthetic */ class ListingProgressUtil$$Lambda$3 implements RequiredStep {
    private static final ListingProgressUtil$$Lambda$3 instance = new ListingProgressUtil$$Lambda$3();

    private ListingProgressUtil$$Lambda$3() {
    }

    public static RequiredStep lambdaFactory$() {
        return instance;
    }

    public boolean completed(Listing listing) {
        return ListingProgressUtil.lambda$generateRequirements$1(listing);
    }
}
