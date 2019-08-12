package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class Listing$$Lambda$2 implements Function {
    private static final Listing$$Lambda$2 instance = new Listing$$Lambda$2();

    private Listing$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Integer.valueOf(((Amenity) obj).mo58911id());
    }
}
