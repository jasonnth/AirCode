package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Function;

final /* synthetic */ class AmenitiesAdapter$$Lambda$7 implements Function {
    private final AmenitiesAdapter arg$1;

    private AmenitiesAdapter$$Lambda$7(AmenitiesAdapter amenitiesAdapter) {
        this.arg$1 = amenitiesAdapter;
    }

    public static Function lambdaFactory$(AmenitiesAdapter amenitiesAdapter) {
        return new AmenitiesAdapter$$Lambda$7(amenitiesAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.createAmenityRow((Amenity) obj);
    }
}
