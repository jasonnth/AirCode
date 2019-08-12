package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class FixItReport$$Lambda$4 implements Predicate {
    private static final FixItReport$$Lambda$4 instance = new FixItReport$$Lambda$4();

    private FixItReport$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return FixItReport.lambda$getNotRequiredToDoItems$3((FixItItem) obj);
    }
}
