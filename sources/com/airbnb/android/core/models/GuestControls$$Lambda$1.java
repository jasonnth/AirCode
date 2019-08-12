package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class GuestControls$$Lambda$1 implements Function {
    private static final GuestControls$$Lambda$1 instance = new GuestControls$$Lambda$1();

    private GuestControls$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return GuestControls.lambda$getLongTermHouseRules$0((StructuredHouseRule) obj);
    }
}
