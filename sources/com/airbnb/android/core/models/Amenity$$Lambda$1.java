package com.airbnb.android.core.models;

import javax.inject.Provider;

final /* synthetic */ class Amenity$$Lambda$1 implements Provider {
    private static final Amenity$$Lambda$1 instance = new Amenity$$Lambda$1();

    private Amenity$$Lambda$1() {
    }

    public static Provider lambdaFactory$() {
        return instance;
    }

    public Object get() {
        return Amenity.lambda$static$0();
    }
}
