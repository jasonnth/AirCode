package com.airbnb.android.booking.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class BookingSummaryFragment$$Lambda$4 implements Action1 {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$4(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static Action1 lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$4(bookingSummaryFragment);
    }

    public void call(Object obj) {
        this.arg$1.getBookingActivity().handleBookingError((AirRequestNetworkException) obj);
    }
}
