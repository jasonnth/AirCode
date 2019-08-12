package com.airbnb.android.core.businesstravel.models;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Predicate;

final /* synthetic */ class BusinessTravelReadyFilterCriteria$$Lambda$4 implements Predicate {
    private static final BusinessTravelReadyFilterCriteria$$Lambda$4 instance = new BusinessTravelReadyFilterCriteria$$Lambda$4();

    private BusinessTravelReadyFilterCriteria$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return BusinessTravelReadyFilterCriteria.lambda$getAmenitiesToFilterOutFromIds$3((Amenity) obj);
    }
}
