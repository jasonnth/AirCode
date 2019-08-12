package com.airbnb.android.core.identity;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class IdentityClient$$Lambda$6 implements Predicate {
    private static final IdentityClient$$Lambda$6 instance = new IdentityClient$$Lambda$6();

    private IdentityClient$$Lambda$6() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return IdentityClient.lambda$onVerificationsFetchComplete$5((AccountVerification) obj);
    }
}
