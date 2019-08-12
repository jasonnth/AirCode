package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class FixItReport$$Lambda$1 implements Predicate {
    private static final FixItReport$$Lambda$1 instance = new FixItReport$$Lambda$1();

    private FixItReport$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return FixItReport.lambda$getAwaitingReviewItems$0((FixItItem) obj);
    }
}
