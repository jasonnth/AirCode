package com.airbnb.android.booking.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class BookingSummaryFragment$$Lambda$7 implements Action1 {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$7(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static Action1 lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$7(bookingSummaryFragment);
    }

    public void call(Object obj) {
        this.arg$1.onBookingError((AirRequestNetworkException) obj);
    }
}
