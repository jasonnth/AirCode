package com.airbnb.android.core.identity;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class FetchIdentityController$$Lambda$2 implements Predicate {
    private static final FetchIdentityController$$Lambda$2 instance = new FetchIdentityController$$Lambda$2();

    private FetchIdentityController$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return FetchIdentityController.lambda$getIncompleteVerificationList$0((AccountVerification) obj);
    }
}
