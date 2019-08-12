package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingArrivalDetailsFragment$$Lambda$2 implements OnClickListener {
    private final BookingArrivalDetailsFragment arg$1;

    private BookingArrivalDetailsFragment$$Lambda$2(BookingArrivalDetailsFragment bookingArrivalDetailsFragment) {
        this.arg$1 = bookingArrivalDetailsFragment;
    }

    public static OnClickListener lambdaFactory$(BookingArrivalDetailsFragment bookingArrivalDetailsFragment) {
        return new BookingArrivalDetailsFragment$$Lambda$2(bookingArrivalDetailsFragment);
    }

    public void onClick(View view) {
        BookingArrivalDetailsFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
