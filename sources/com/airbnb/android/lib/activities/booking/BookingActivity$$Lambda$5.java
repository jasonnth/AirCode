package com.airbnb.android.lib.activities.booking;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class BookingActivity$$Lambda$5 implements Predicate {
    private static final BookingActivity$$Lambda$5 instance = new BookingActivity$$Lambda$5();

    private BookingActivity$$Lambda$5() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return BookingActivity.lambda$startVerificationFlowIfNeeded$4((AccountVerification) obj);
    }
}
