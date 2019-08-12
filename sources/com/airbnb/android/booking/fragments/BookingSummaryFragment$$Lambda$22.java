package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.models.Reservation;
import p032rx.functions.Action1;

final /* synthetic */ class BookingSummaryFragment$$Lambda$22 implements Action1 {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$22(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static Action1 lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$22(bookingSummaryFragment);
    }

    public void call(Object obj) {
        this.arg$1.onBookingResult((Reservation) obj);
    }
}
