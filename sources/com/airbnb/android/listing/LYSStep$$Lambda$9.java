package com.airbnb.android.listing;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class LYSStep$$Lambda$9 implements Predicate {
    private static final LYSStep$$Lambda$9 instance = new LYSStep$$Lambda$9();

    private LYSStep$$Lambda$9() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return LYSStep.lambda$getIncompleteIdentitySteps$8((AccountVerification) obj);
    }
}
