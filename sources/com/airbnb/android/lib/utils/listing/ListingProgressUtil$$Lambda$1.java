package com.airbnb.android.lib.utils.listing;

import com.airbnb.android.core.models.Listing;

final /* synthetic */ class ListingProgressUtil$$Lambda$1 implements RequiredStep {
    private static final ListingProgressUtil$$Lambda$1 instance = new ListingProgressUtil$$Lambda$1();

    private ListingProgressUtil$$Lambda$1() {
    }

    public static RequiredStep lambdaFactory$() {
        return instance;
    }

    public boolean completed(Listing listing) {
        return ListingProgressUtil.lambda$generateRequirements$0(listing);
    }
}
