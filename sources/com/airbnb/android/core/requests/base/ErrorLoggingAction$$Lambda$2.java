package com.airbnb.android.core.requests.base;

import com.google.common.base.Predicate;

final /* synthetic */ class ErrorLoggingAction$$Lambda$2 implements Predicate {
    private static final ErrorLoggingAction$$Lambda$2 instance = new ErrorLoggingAction$$Lambda$2();

    private ErrorLoggingAction$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ErrorLoggingAction.lambda$buildSourceStackTrace$1((StackTraceElement) obj);
    }
}
