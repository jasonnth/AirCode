package com.airbnb.android.listing.adapters;

import com.google.common.base.Predicate;
import java.util.HashMap;

final /* synthetic */ class AmenitiesAdapter$$Lambda$3 implements Predicate {
    private final AmenitiesAdapter arg$1;
    private final HashMap arg$2;

    private AmenitiesAdapter$$Lambda$3(AmenitiesAdapter amenitiesAdapter, HashMap hashMap) {
        this.arg$1 = amenitiesAdapter;
        this.arg$2 = hashMap;
    }

    public static Predicate lambdaFactory$(AmenitiesAdapter amenitiesAdapter, HashMap hashMap) {
        return new AmenitiesAdapter$$Lambda$3(amenitiesAdapter, hashMap);
    }

    public boolean apply(Object obj) {
        return ((Boolean) this.arg$2.get((Integer) obj)).equals(this.arg$1.amenityMap.get((Integer) obj));
    }
}
