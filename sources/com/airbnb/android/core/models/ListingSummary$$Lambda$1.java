package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class ListingSummary$$Lambda$1 implements Predicate {
    private final long arg$1;

    private ListingSummary$$Lambda$1(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new ListingSummary$$Lambda$1(j);
    }

    public boolean apply(Object obj) {
        return ListingSummary.lambda$isUserCohost$0(this.arg$1, (User) obj);
    }
}
