package com.airbnb.android.lib.activities.booking;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class BookingActivity$$Lambda$6 implements Predicate {
    private static final BookingActivity$$Lambda$6 instance = new BookingActivity$$Lambda$6();

    private BookingActivity$$Lambda$6() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return BookingActivity.lambda$startVerificationFlowIfNeeded$5((AccountVerification) obj);
    }
}
