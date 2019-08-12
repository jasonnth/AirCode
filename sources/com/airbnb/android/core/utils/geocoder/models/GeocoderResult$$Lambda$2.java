package com.airbnb.android.core.utils.geocoder.models;

import com.google.common.base.Predicate;

final /* synthetic */ class GeocoderResult$$Lambda$2 implements Predicate {
    private static final GeocoderResult$$Lambda$2 instance = new GeocoderResult$$Lambda$2();

    private GeocoderResult$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return GeocoderResult.lambda$getSubLocalities$0((String) obj);
    }
}
