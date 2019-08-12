package com.airbnb.android.identity;

import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Function;

final /* synthetic */ class AccountVerificationStartActivity$$Lambda$1 implements Function {
    private static final AccountVerificationStartActivity$$Lambda$1 instance = new AccountVerificationStartActivity$$Lambda$1();

    private AccountVerificationStartActivity$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return AccountVerificationStep.toAccountVerificationStep(((AccountVerification) obj).getType());
    }
}
