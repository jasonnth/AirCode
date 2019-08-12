package com.airbnb.android.core.utils.geocoder.models;

import com.airbnb.android.core.utils.geocoder.AddressComponentType;
import com.google.common.base.Function;

final /* synthetic */ class GeocoderResult$$Lambda$1 implements Function {
    private final GeocoderResult arg$1;

    private GeocoderResult$$Lambda$1(GeocoderResult geocoderResult) {
        this.arg$1 = geocoderResult;
    }

    public static Function lambdaFactory$(GeocoderResult geocoderResult) {
        return new GeocoderResult$$Lambda$1(geocoderResult);
    }

    public Object apply(Object obj) {
        return this.arg$1.getShortName((AddressComponentType) obj);
    }
}
