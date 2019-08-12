package com.airbnb.android.core.identity;

import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class FetchIdentityController$$Lambda$3 implements Predicate {
    private final FetchIdentityController arg$1;
    private final VerificationFlow arg$2;

    private FetchIdentityController$$Lambda$3(FetchIdentityController fetchIdentityController, VerificationFlow verificationFlow) {
        this.arg$1 = fetchIdentityController;
        this.arg$2 = verificationFlow;
    }

    public static Predicate lambdaFactory$(FetchIdentityController fetchIdentityController, VerificationFlow verificationFlow) {
        return new FetchIdentityController$$Lambda$3(fetchIdentityController, verificationFlow);
    }

    public boolean apply(Object obj) {
        return this.arg$1.filterPhoneAndEmail((AccountVerification) obj, this.arg$2);
    }
}
