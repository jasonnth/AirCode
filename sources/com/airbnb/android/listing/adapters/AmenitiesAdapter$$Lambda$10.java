package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;

final /* synthetic */ class AmenitiesAdapter$$Lambda$10 implements Predicate {
    private final AmenitiesAdapter arg$1;
    private final Listing arg$2;

    private AmenitiesAdapter$$Lambda$10(AmenitiesAdapter amenitiesAdapter, Listing listing) {
        this.arg$1 = amenitiesAdapter;
        this.arg$2 = listing;
    }

    public static Predicate lambdaFactory$(AmenitiesAdapter amenitiesAdapter, Listing listing) {
        return new AmenitiesAdapter$$Lambda$10(amenitiesAdapter, listing);
    }

    public boolean apply(Object obj) {
        return this.arg$1.shouldShowAmenity((Amenity) obj, this.arg$2);
    }
}
