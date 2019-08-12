package com.airbnb.android.core.identity;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class IdentityClient$$Lambda$3 implements Predicate {
    private final boolean arg$1;

    private IdentityClient$$Lambda$3(boolean z) {
        this.arg$1 = z;
    }

    public static Predicate lambdaFactory$(boolean z) {
        return new IdentityClient$$Lambda$3(z);
    }

    public boolean apply(Object obj) {
        return IdentityClient.lambda$onVerificationsFetchComplete$2(this.arg$1, (AccountVerification) obj);
    }
}
