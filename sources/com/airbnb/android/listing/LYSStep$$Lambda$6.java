package com.airbnb.android.listing;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class LYSStep$$Lambda$6 implements Predicate {
    private static final LYSStep$$Lambda$6 instance = new LYSStep$$Lambda$6();

    private LYSStep$$Lambda$6() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return LYSStep.lambda$getIncompleteVerificationSteps$5((AccountVerification) obj);
    }
}
