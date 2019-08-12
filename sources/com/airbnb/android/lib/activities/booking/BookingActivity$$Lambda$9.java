package com.airbnb.android.lib.activities.booking;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingActivity$$Lambda$9 implements OnClickListener {
    private final BookingActivity arg$1;

    private BookingActivity$$Lambda$9(BookingActivity bookingActivity) {
        this.arg$1 = bookingActivity;
    }

    public static OnClickListener lambdaFactory$(BookingActivity bookingActivity) {
        return new BookingActivity$$Lambda$9(bookingActivity);
    }

    public void onClick(View view) {
        this.arg$1.agreeToCurrencyUpdate();
    }
}
