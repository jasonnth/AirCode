package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class CheckInGuide$$Lambda$1 implements Function {
    private static final CheckInGuide$$Lambda$1 instance = new CheckInGuide$$Lambda$1();

    private CheckInGuide$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return CheckInStep.getSampleCheckinStep(((Integer) obj).intValue());
    }
}
