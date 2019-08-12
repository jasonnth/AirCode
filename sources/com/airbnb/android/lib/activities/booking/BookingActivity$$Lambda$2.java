package com.airbnb.android.lib.activities.booking;

import android.widget.Toast;
import p032rx.functions.Action1;

final /* synthetic */ class BookingActivity$$Lambda$2 implements Action1 {
    private final BookingActivity arg$1;

    private BookingActivity$$Lambda$2(BookingActivity bookingActivity) {
        this.arg$1 = bookingActivity;
    }

    public static Action1 lambdaFactory$(BookingActivity bookingActivity) {
        return new BookingActivity$$Lambda$2(bookingActivity);
    }

    public void call(Object obj) {
        Toast.makeText(this.arg$1, "Error fetching listing", 0).show();
    }
}
