package com.airbnb.android.listing;

import com.google.common.base.Predicate;

final /* synthetic */ class LYSStep$$Lambda$1 implements Predicate {
    private final LYSStep arg$1;

    private LYSStep$$Lambda$1(LYSStep lYSStep) {
        this.arg$1 = lYSStep;
    }

    public static Predicate lambdaFactory$(LYSStep lYSStep) {
        return new LYSStep$$Lambda$1(lYSStep);
    }

    public boolean apply(Object obj) {
        return ((LYSStep) obj).isBeforeOrSame(this.arg$1);
    }
}
