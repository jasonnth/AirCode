package com.airbnb.android.explore;

import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Function;

final /* synthetic */ class ExploreJitneyLogger$$Lambda$6 implements Function {
    private static final ExploreJitneyLogger$$Lambda$6 instance = new ExploreJitneyLogger$$Lambda$6();

    private ExploreJitneyLogger$$Lambda$6() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf((long) ((Amenity) obj).f8471id);
    }
}
