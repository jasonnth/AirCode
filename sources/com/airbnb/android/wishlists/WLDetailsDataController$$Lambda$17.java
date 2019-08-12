package com.airbnb.android.wishlists;

import com.airbnb.android.core.models.WishListedPlace;
import com.google.common.base.Predicate;

final /* synthetic */ class WLDetailsDataController$$Lambda$17 implements Predicate {
    private static final WLDetailsDataController$$Lambda$17 instance = new WLDetailsDataController$$Lambda$17();

    private WLDetailsDataController$$Lambda$17() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((WishListedPlace) obj).hasGuidebookPlace();
    }
}
