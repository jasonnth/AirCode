package com.airbnb.android.explore;

import com.google.common.base.Function;

final /* synthetic */ class ExploreJitneyLogger$$Lambda$9 implements Function {
    private static final ExploreJitneyLogger$$Lambda$9 instance = new ExploreJitneyLogger$$Lambda$9();

    private ExploreJitneyLogger$$Lambda$9() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf((long) ((Integer) obj).intValue());
    }
}
