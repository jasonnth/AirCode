package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.models.CheckinTimeSelectionOptions;
import com.google.common.base.Predicate;

final /* synthetic */ class BookingSummaryFragment$$Lambda$11 implements Predicate {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$11(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static Predicate lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$11(bookingSummaryFragment);
    }

    public boolean apply(Object obj) {
        return ((CheckinTimeSelectionOptions) obj).getFormattedHour().equalsIgnoreCase(this.arg$1.getReservationDetails().checkInHour());
    }
}
