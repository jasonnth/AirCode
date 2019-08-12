package com.airbnb.android.core.requests.base;

import com.google.common.base.Predicate;

final /* synthetic */ class ErrorLoggingAction$$Lambda$1 implements Predicate {
    private static final ErrorLoggingAction$$Lambda$1 instance = new ErrorLoggingAction$$Lambda$1();

    private ErrorLoggingAction$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((StackTraceElement) obj).getClassName().contains("airbnb");
    }
}
