package com.airbnb.android.core.models.verifications;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Function;

final /* synthetic */ class VerificationsState$$Lambda$2 implements Function {
    private static final VerificationsState$$Lambda$2 instance = new VerificationsState$$Lambda$2();

    private VerificationsState$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return VerificationsState.lambda$initializeFromIncompleteAccountVerifications$1((AccountVerification) obj);
    }
}
