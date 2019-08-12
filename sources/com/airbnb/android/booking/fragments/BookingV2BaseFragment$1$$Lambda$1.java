package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingV2BaseFragment$1$$Lambda$1 implements OnClickListener {
    private final C55811 arg$1;

    private BookingV2BaseFragment$1$$Lambda$1(C55811 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C55811 r1) {
        return new BookingV2BaseFragment$1$$Lambda$1(r1);
    }

    public void onClick(View view) {
        BookingV2BaseFragment.this.updateGuestDetails();
    }
}
