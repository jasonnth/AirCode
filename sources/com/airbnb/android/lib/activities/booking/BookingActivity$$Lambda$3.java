package com.airbnb.android.lib.activities.booking;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class BookingActivity$$Lambda$3 implements Action1 {
    private final BookingActivity arg$1;

    private BookingActivity$$Lambda$3(BookingActivity bookingActivity) {
        this.arg$1 = bookingActivity;
    }

    public static Action1 lambdaFactory$(BookingActivity bookingActivity) {
        return new BookingActivity$$Lambda$3(bookingActivity);
    }

    public void call(Object obj) {
        BookingActivity.lambda$new$2(this.arg$1, (ReservationResponse) obj);
    }
}
