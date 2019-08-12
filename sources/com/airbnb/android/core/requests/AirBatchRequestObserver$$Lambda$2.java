package com.airbnb.android.core.requests;

import com.google.common.base.Predicate;

final /* synthetic */ class AirBatchRequestObserver$$Lambda$2 implements Predicate {
    private static final AirBatchRequestObserver$$Lambda$2 instance = new AirBatchRequestObserver$$Lambda$2();

    private AirBatchRequestObserver$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return AirBatchRequestObserver.lambda$buildSourceStackTrace$1((StackTraceElement) obj);
    }
}
