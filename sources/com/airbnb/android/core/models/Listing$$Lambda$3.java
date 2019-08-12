package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class Listing$$Lambda$3 implements Function {
    private static final Listing$$Lambda$3 instance = new Listing$$Lambda$3();

    private Listing$$Lambda$3() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Amenity.forId(((Integer) obj).intValue());
    }
}
