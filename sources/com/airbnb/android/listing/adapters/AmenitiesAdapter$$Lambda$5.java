package com.airbnb.android.listing.adapters;

import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Function;

final /* synthetic */ class AmenitiesAdapter$$Lambda$5 implements Function {
    private static final AmenitiesAdapter$$Lambda$5 instance = new AmenitiesAdapter$$Lambda$5();

    private AmenitiesAdapter$$Lambda$5() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return AmenitiesAdapter.lambda$setInputEnabled$4((EpoxyModel) obj);
    }
}
