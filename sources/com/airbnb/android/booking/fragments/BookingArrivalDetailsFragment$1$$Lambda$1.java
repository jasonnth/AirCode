package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingArrivalDetailsFragment$1$$Lambda$1 implements OnClickListener {
    private final C55551 arg$1;

    private BookingArrivalDetailsFragment$1$$Lambda$1(C55551 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C55551 r1) {
        return new BookingArrivalDetailsFragment$1$$Lambda$1(r1);
    }

    public void onClick(View view) {
        BookingArrivalDetailsFragment.this.confirmArrivalTime();
    }
}
