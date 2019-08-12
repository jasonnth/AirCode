package com.airbnb.android.booking.steps;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class IdentityBookingStep$$Lambda$1 implements Predicate {
    private final boolean arg$1;

    private IdentityBookingStep$$Lambda$1(boolean z) {
        this.arg$1 = z;
    }

    public static Predicate lambdaFactory$(boolean z) {
        return new IdentityBookingStep$$Lambda$1(z);
    }

    public boolean apply(Object obj) {
        return IdentityBookingStep.lambda$checkForIncompleteVerifications$0(this.arg$1, (AccountVerification) obj);
    }
}
