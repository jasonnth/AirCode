package com.airbnb.android.core.businesstravel.models;

import com.airbnb.android.core.enums.PropertyType;
import com.google.common.base.Function;

final /* synthetic */ class BusinessTravelReadyFilterCriteria$$Lambda$6 implements Function {
    private static final BusinessTravelReadyFilterCriteria$$Lambda$6 instance = new BusinessTravelReadyFilterCriteria$$Lambda$6();

    private BusinessTravelReadyFilterCriteria$$Lambda$6() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PropertyType.getTypeFromKey(((Integer) obj).intValue());
    }
}
