package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.models.PreBookingQuestion;
import com.google.common.base.Predicate;

final /* synthetic */ class BookingEditTextFragment$$Lambda$3 implements Predicate {
    private static final BookingEditTextFragment$$Lambda$3 instance = new BookingEditTextFragment$$Lambda$3();

    private BookingEditTextFragment$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((PreBookingQuestion) obj).isChecked();
    }
}
