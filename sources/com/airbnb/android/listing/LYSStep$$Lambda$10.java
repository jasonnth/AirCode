package com.airbnb.android.listing;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class LYSStep$$Lambda$10 implements Predicate {
    private static final LYSStep$$Lambda$10 instance = new LYSStep$$Lambda$10();

    private LYSStep$$Lambda$10() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return LYSStep.lambda$getIncompleteIdentitySteps$9((AccountVerification) obj);
    }
}
