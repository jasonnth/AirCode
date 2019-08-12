package com.airbnb.android.explore;

import com.airbnb.android.core.models.C6120RoomType;
import com.google.common.base.Function;

final /* synthetic */ class ExploreJitneyLogger$$Lambda$7 implements Function {
    private static final ExploreJitneyLogger$$Lambda$7 instance = new ExploreJitneyLogger$$Lambda$7();

    private ExploreJitneyLogger$$Lambda$7() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((C6120RoomType) obj).toString();
    }
}
