package com.airbnb.android.explore.requests;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Function;

final /* synthetic */ class ExploreUpdateSavedSearchRequest$$Lambda$2 implements Function {
    private static final ExploreUpdateSavedSearchRequest$$Lambda$2 instance = new ExploreUpdateSavedSearchRequest$$Lambda$2();

    private ExploreUpdateSavedSearchRequest$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Integer.valueOf(((Amenity) obj).f8471id);
    }
}
