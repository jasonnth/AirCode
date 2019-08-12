package com.airbnb.android.core.requests;

import com.google.common.base.Predicate;

final /* synthetic */ class AirBatchRequestObserver$$Lambda$1 implements Predicate {
    private static final AirBatchRequestObserver$$Lambda$1 instance = new AirBatchRequestObserver$$Lambda$1();

    private AirBatchRequestObserver$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((StackTraceElement) obj).getClassName().contains("airbnb");
    }
}
