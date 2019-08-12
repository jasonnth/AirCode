package com.airbnb.android.explore.requests;

import com.airbnb.android.core.models.C6120RoomType;
import com.google.common.base.Function;

final /* synthetic */ class ExploreUpdateSavedSearchRequest$$Lambda$1 implements Function {
    private static final ExploreUpdateSavedSearchRequest$$Lambda$1 instance = new ExploreUpdateSavedSearchRequest$$Lambda$1();

    private ExploreUpdateSavedSearchRequest$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((C6120RoomType) obj).key;
    }
}
