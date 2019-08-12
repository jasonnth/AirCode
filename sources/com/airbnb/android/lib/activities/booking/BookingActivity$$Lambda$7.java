package com.airbnb.android.lib.activities.booking;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingActivity$$Lambda$7 implements OnClickListener {
    private final BookingActivity arg$1;

    private BookingActivity$$Lambda$7(BookingActivity bookingActivity) {
        this.arg$1 = bookingActivity;
    }

    public static OnClickListener lambdaFactory$(BookingActivity bookingActivity) {
        return new BookingActivity$$Lambda$7(bookingActivity);
    }

    public void onClick(View view) {
        this.arg$1.showPaymentOptions();
    }
}
