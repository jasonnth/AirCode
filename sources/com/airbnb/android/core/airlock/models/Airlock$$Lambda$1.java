package com.airbnb.android.core.airlock.models;

import com.google.common.base.Predicate;

final /* synthetic */ class Airlock$$Lambda$1 implements Predicate {
    private final Airlock arg$1;

    private Airlock$$Lambda$1(Airlock airlock) {
        this.arg$1 = airlock;
    }

    public static Predicate lambdaFactory$(Airlock airlock) {
        return new Airlock$$Lambda$1(airlock);
    }

    public boolean apply(Object obj) {
        return this.arg$1.isFrictionSupported((AirlockFrictionType) obj);
    }
}
