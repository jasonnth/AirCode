package com.airbnb.android.lib.utils.listing;

import com.airbnb.android.core.models.Listing;

final /* synthetic */ class ListingProgressUtil$$Lambda$5 implements RequiredStep {
    private static final ListingProgressUtil$$Lambda$5 instance = new ListingProgressUtil$$Lambda$5();

    private ListingProgressUtil$$Lambda$5() {
    }

    public static RequiredStep lambdaFactory$() {
        return instance;
    }

    public boolean completed(Listing listing) {
        return ListingProgressUtil.lambda$generateRequirements$2(listing);
    }
}
