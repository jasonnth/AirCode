package com.airbnb.android.core.identity;

import com.airbnb.android.core.enums.VerificationFlow;
import com.google.common.base.Predicate;

final /* synthetic */ class FetchIdentityController$$Lambda$1 implements Predicate {
    private static final FetchIdentityController$$Lambda$1 instance = new FetchIdentityController$$Lambda$1();

    private FetchIdentityController$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((VerificationFlow) obj).requiresIdentityTreatment();
    }
}
