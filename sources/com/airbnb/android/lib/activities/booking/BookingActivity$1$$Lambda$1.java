package com.airbnb.android.lib.activities.booking;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class BookingActivity$1$$Lambda$1 implements Predicate {
    private final boolean arg$1;

    private BookingActivity$1$$Lambda$1(boolean z) {
        this.arg$1 = z;
    }

    public static Predicate lambdaFactory$(boolean z) {
        return new BookingActivity$1$$Lambda$1(z);
    }

    public boolean apply(Object obj) {
        return C66791.lambda$onVerificationsFetchComplete$0(this.arg$1, (AccountVerification) obj);
    }
}
