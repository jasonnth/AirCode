package com.airbnb.android.listyourspacedls.utils;

import com.airbnb.android.listing.LYSStep;
import com.google.common.base.Predicate;

final /* synthetic */ class LYSLastSeenStepUtil$$Lambda$1 implements Predicate {
    private final String arg$1;

    private LYSLastSeenStepUtil$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new LYSLastSeenStepUtil$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return LYSLastSeenStepUtil.lambda$getStepFromConstant$0(this.arg$1, (LYSStep) obj);
    }
}
