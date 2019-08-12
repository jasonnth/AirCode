package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class FixItReport$$Lambda$3 implements Predicate {
    private static final FixItReport$$Lambda$3 instance = new FixItReport$$Lambda$3();

    private FixItReport$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return FixItReport.lambda$getRequiredToDoItems$2((FixItItem) obj);
    }
}
