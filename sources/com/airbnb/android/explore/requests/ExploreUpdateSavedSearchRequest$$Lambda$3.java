package com.airbnb.android.explore.requests;

import com.airbnb.android.core.enums.PropertyType;
import com.google.common.base.Function;

final /* synthetic */ class ExploreUpdateSavedSearchRequest$$Lambda$3 implements Function {
    private static final ExploreUpdateSavedSearchRequest$$Lambda$3 instance = new ExploreUpdateSavedSearchRequest$$Lambda$3();

    private ExploreUpdateSavedSearchRequest$$Lambda$3() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Integer.valueOf(((PropertyType) obj).serverDescKey);
    }
}
