package com.airbnb.android.core.intents;

import com.airbnb.android.core.models.ListingExpectation;
import com.google.common.base.Function;

final /* synthetic */ class ReactNativeIntents$$Lambda$1 implements Function {
    private static final ReactNativeIntents$$Lambda$1 instance = new ReactNativeIntents$$Lambda$1();

    private ReactNativeIntents$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ReactNativeIntents.getListingExpectationBundle((ListingExpectation) obj);
    }
}
