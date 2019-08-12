package com.airbnb.android.listing.adapters;

import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Predicate;

final /* synthetic */ class AmenitiesAdapter$$Lambda$4 implements Predicate {
    private static final AmenitiesAdapter$$Lambda$4 instance = new AmenitiesAdapter$$Lambda$4();

    private AmenitiesAdapter$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return AmenitiesAdapter.lambda$setInputEnabled$3((EpoxyModel) obj);
    }
}
