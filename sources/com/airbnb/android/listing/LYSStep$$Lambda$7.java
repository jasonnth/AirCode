package com.airbnb.android.listing;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class LYSStep$$Lambda$7 implements Predicate {
    private static final LYSStep$$Lambda$7 instance = new LYSStep$$Lambda$7();

    private LYSStep$$Lambda$7() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return LYSStep.lambda$getIncompleteVerificationSteps$6((AccountVerification) obj);
    }
}
