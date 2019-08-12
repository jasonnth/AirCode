package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingV2BaseFragment$$Lambda$5 implements OnClickListener {
    private final BookingV2BaseFragment arg$1;

    private BookingV2BaseFragment$$Lambda$5(BookingV2BaseFragment bookingV2BaseFragment) {
        this.arg$1 = bookingV2BaseFragment;
    }

    public static OnClickListener lambdaFactory$(BookingV2BaseFragment bookingV2BaseFragment) {
        return new BookingV2BaseFragment$$Lambda$5(bookingV2BaseFragment);
    }

    public void onClick(View view) {
        this.arg$1.clickSeePriceDetails();
    }
}
