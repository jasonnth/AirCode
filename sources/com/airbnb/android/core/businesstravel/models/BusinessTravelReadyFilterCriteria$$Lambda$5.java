package com.airbnb.android.core.businesstravel.models;

import com.airbnb.android.core.models.C6120RoomType;
import com.google.common.base.Function;

final /* synthetic */ class BusinessTravelReadyFilterCriteria$$Lambda$5 implements Function {
    private static final BusinessTravelReadyFilterCriteria$$Lambda$5 instance = new BusinessTravelReadyFilterCriteria$$Lambda$5();

    private BusinessTravelReadyFilterCriteria$$Lambda$5() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return C6120RoomType.fromKey((String) obj);
    }
}
