package com.airbnb.android.explore.requests;

import com.airbnb.android.core.enums.PropertyType;
import com.google.common.base.Function;

final /* synthetic */ class ExploreRequestParamsBuilder$$Lambda$1 implements Function {
    private static final ExploreRequestParamsBuilder$$Lambda$1 instance = new ExploreRequestParamsBuilder$$Lambda$1();

    private ExploreRequestParamsBuilder$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Integer.valueOf(((PropertyType) obj).serverDescKey);
    }
}
