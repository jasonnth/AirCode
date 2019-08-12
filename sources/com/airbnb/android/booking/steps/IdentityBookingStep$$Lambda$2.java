package com.airbnb.android.booking.steps;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class IdentityBookingStep$$Lambda$2 implements Predicate {
    private final boolean arg$1;

    private IdentityBookingStep$$Lambda$2(boolean z) {
        this.arg$1 = z;
    }

    public static Predicate lambdaFactory$(boolean z) {
        return new IdentityBookingStep$$Lambda$2(z);
    }

    public boolean apply(Object obj) {
        return IdentityBookingStep.lambda$checkForIncompleteVerifications$1(this.arg$1, (AccountVerification) obj);
    }
}
