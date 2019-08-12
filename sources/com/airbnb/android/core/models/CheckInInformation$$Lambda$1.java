package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class CheckInInformation$$Lambda$1 implements Predicate {
    private static final CheckInInformation$$Lambda$1 instance = new CheckInInformation$$Lambda$1();

    private CheckInInformation$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return CheckInInformation.lambda$selfCheckinMethods$1((CheckInInformation) obj);
    }
}
