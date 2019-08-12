package com.airbnb.android.core.models.verifications;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class VerificationsState$$Lambda$1 implements Predicate {
    private static final VerificationsState$$Lambda$1 instance = new VerificationsState$$Lambda$1();

    private VerificationsState$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return VerificationsState.lambda$initializeFromIncompleteAccountVerifications$0((AccountVerification) obj);
    }
}
