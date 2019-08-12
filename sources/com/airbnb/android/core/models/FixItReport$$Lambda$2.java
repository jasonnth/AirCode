package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class FixItReport$$Lambda$2 implements Predicate {
    private static final FixItReport$$Lambda$2 instance = new FixItReport$$Lambda$2();

    private FixItReport$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return FixItReport.lambda$getApprovedItems$1((FixItItem) obj);
    }
}
