package com.airbnb.android.core.utils.geocoder.models;

import com.airbnb.android.core.utils.geocoder.AddressComponentType;
import com.google.common.base.Predicate;
import java.util.List;

final /* synthetic */ class AutocompletePrediction$$Lambda$1 implements Predicate {
    private final List arg$1;

    private AutocompletePrediction$$Lambda$1(List list) {
        this.arg$1 = list;
    }

    public static Predicate lambdaFactory$(List list) {
        return new AutocompletePrediction$$Lambda$1(list);
    }

    public boolean apply(Object obj) {
        return AutocompletePrediction.lambda$getLocationTag$0(this.arg$1, (AddressComponentType) obj);
    }
}
