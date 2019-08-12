package com.airbnb.android.listing.adapters;

import com.airbnb.android.listing.AmenityGroup;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

final /* synthetic */ class AmenitiesAdapter$$Lambda$9 implements Function {
    private static final AmenitiesAdapter$$Lambda$9 instance = new AmenitiesAdapter$$Lambda$9();

    private AmenitiesAdapter$$Lambda$9() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Lists.newArrayList((E[]) ((AmenityGroup) obj).getAmenities());
    }
}
