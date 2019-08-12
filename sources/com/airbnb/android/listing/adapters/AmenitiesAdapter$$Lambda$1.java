package com.airbnb.android.listing.adapters;

import com.google.common.base.Predicate;

final /* synthetic */ class AmenitiesAdapter$$Lambda$1 implements Predicate {
    private final AmenitiesAdapter arg$1;

    private AmenitiesAdapter$$Lambda$1(AmenitiesAdapter amenitiesAdapter) {
        this.arg$1 = amenitiesAdapter;
    }

    public static Predicate lambdaFactory$(AmenitiesAdapter amenitiesAdapter) {
        return new AmenitiesAdapter$$Lambda$1(amenitiesAdapter);
    }

    public boolean apply(Object obj) {
        return AmenitiesAdapter.lambda$getSelectedAmenities$0(this.arg$1, (Integer) obj);
    }
}
