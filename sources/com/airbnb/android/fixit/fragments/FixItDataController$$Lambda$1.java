package com.airbnb.android.fixit.fragments;

import com.airbnb.android.core.models.FixItItem;
import com.google.common.base.Predicate;

final /* synthetic */ class FixItDataController$$Lambda$1 implements Predicate {
    private final long arg$1;

    private FixItDataController$$Lambda$1(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new FixItDataController$$Lambda$1(j);
    }

    public boolean apply(Object obj) {
        return FixItDataController.lambda$getItem$0(this.arg$1, (FixItItem) obj);
    }
}
