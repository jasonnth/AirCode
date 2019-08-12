package com.airbnb.android.lib.activities.booking;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class BookingActivity$$Lambda$4 implements Action1 {
    private final BookingActivity arg$1;

    private BookingActivity$$Lambda$4(BookingActivity bookingActivity) {
        this.arg$1 = bookingActivity;
    }

    public static Action1 lambdaFactory$(BookingActivity bookingActivity) {
        return new BookingActivity$$Lambda$4(bookingActivity);
    }

    public void call(Object obj) {
        BookingActivity.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
