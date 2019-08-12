package com.airbnb.android.core.businesstravel.models;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Predicate;

final /* synthetic */ class BusinessTravelReadyFilterCriteria$$Lambda$2 implements Predicate {
    private static final BusinessTravelReadyFilterCriteria$$Lambda$2 instance = new BusinessTravelReadyFilterCriteria$$Lambda$2();

    private BusinessTravelReadyFilterCriteria$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return BusinessTravelReadyFilterCriteria.lambda$getHostingAmenitiesFromIds$1((Amenity) obj);
    }
}
