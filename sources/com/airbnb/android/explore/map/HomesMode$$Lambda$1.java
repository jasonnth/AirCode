package com.airbnb.android.explore.map;

import com.airbnb.android.core.models.SearchResult;
import com.google.common.base.Function;

final /* synthetic */ class HomesMode$$Lambda$1 implements Function {
    private static final HomesMode$$Lambda$1 instance = new HomesMode$$Lambda$1();

    private HomesMode$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return HomesMode.lambda$initDataAndAddToCarousel$0((SearchResult) obj);
    }
}
