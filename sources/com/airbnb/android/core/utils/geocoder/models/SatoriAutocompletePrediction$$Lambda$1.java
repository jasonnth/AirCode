package com.airbnb.android.core.utils.geocoder.models;

import com.airbnb.android.core.utils.geocoder.AddressComponentType;
import com.google.common.base.Predicate;
import java.util.List;

final /* synthetic */ class SatoriAutocompletePrediction$$Lambda$1 implements Predicate {
    private final List arg$1;

    private SatoriAutocompletePrediction$$Lambda$1(List list) {
        this.arg$1 = list;
    }

    public static Predicate lambdaFactory$(List list) {
        return new SatoriAutocompletePrediction$$Lambda$1(list);
    }

    public boolean apply(Object obj) {
        return SatoriAutocompletePrediction.lambda$getLocationTag$0(this.arg$1, (AddressComponentType) obj);
    }
}
