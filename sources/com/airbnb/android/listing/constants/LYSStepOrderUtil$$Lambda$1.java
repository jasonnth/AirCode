package com.airbnb.android.listing.constants;

import com.airbnb.android.listing.LYSStep;
import com.google.common.base.Predicate;

final /* synthetic */ class LYSStepOrderUtil$$Lambda$1 implements Predicate {
    private static final LYSStepOrderUtil$$Lambda$1 instance = new LYSStepOrderUtil$$Lambda$1();

    private LYSStepOrderUtil$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return LYSStepOrderUtil.lambda$getOrderedStepsWithoutCompletion$0((LYSStep) obj);
    }
}
