package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class PlaceActivity$$Lambda$1 implements Function {
    private static final PlaceActivity$$Lambda$1 instance = new PlaceActivity$$Lambda$1();

    private PlaceActivity$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((Photo) obj).getXLargeUrl();
    }
}
