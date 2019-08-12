package com.airbnb.android.core.utils;

import java.util.concurrent.Callable;

final /* synthetic */ class ColdStartExperimentDeliverer$$Lambda$1 implements Callable {
    private static final ColdStartExperimentDeliverer$$Lambda$1 instance = new ColdStartExperimentDeliverer$$Lambda$1();

    private ColdStartExperimentDeliverer$$Lambda$1() {
    }

    public static Callable lambdaFactory$() {
        return instance;
    }

    public Object call() {
        return ColdStartExperimentDeliverer.lambda$loadAssignment$0();
    }
}
