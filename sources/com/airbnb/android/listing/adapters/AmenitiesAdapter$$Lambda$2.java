package com.airbnb.android.listing.adapters;

import com.google.common.base.Predicate;

final /* synthetic */ class AmenitiesAdapter$$Lambda$2 implements Predicate {
    private final AmenitiesAdapter arg$1;

    private AmenitiesAdapter$$Lambda$2(AmenitiesAdapter amenitiesAdapter) {
        this.arg$1 = amenitiesAdapter;
    }

    public static Predicate lambdaFactory$(AmenitiesAdapter amenitiesAdapter) {
        return new AmenitiesAdapter$$Lambda$2(amenitiesAdapter);
    }

    public boolean apply(Object obj) {
        return ((Boolean) this.arg$1.amenityMap.get((Integer) obj)).booleanValue();
    }
}
