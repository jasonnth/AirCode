package com.airbnb.android.core.businesstravel.models;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Function;

final /* synthetic */ class BusinessTravelReadyFilterCriteria$$Lambda$1 implements Function {
    private static final BusinessTravelReadyFilterCriteria$$Lambda$1 instance = new BusinessTravelReadyFilterCriteria$$Lambda$1();

    private BusinessTravelReadyFilterCriteria$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Amenity.forId(((Integer) obj).intValue());
    }
}
