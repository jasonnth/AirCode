package com.airbnb.android.listing;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Function;

final /* synthetic */ class AmenityGroup$$Lambda$1 implements Function {
    private static final AmenityGroup$$Lambda$1 instance = new AmenityGroup$$Lambda$1();

    private AmenityGroup$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Amenity.forId(((Integer) obj).intValue());
    }
}
