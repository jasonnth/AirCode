package com.airbnb.android.lib.activities.booking;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class BookingActivity$$Lambda$12 implements Predicate {
    private static final BookingActivity$$Lambda$12 instance = new BookingActivity$$Lambda$12();

    private BookingActivity$$Lambda$12() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((AccountVerification) obj).getType().equals(AccountVerification.SCANID);
    }
}
