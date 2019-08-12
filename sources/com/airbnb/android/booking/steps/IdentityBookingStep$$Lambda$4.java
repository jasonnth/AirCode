package com.airbnb.android.booking.steps;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class IdentityBookingStep$$Lambda$4 implements Predicate {
    private static final IdentityBookingStep$$Lambda$4 instance = new IdentityBookingStep$$Lambda$4();

    private IdentityBookingStep$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return IdentityBookingStep.lambda$checkForIncompleteVerifications$3((AccountVerification) obj);
    }
}
