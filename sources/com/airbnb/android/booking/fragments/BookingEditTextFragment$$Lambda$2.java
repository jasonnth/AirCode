package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingEditTextFragment$$Lambda$2 implements OnClickListener {
    private final BookingEditTextFragment arg$1;

    private BookingEditTextFragment$$Lambda$2(BookingEditTextFragment bookingEditTextFragment) {
        this.arg$1 = bookingEditTextFragment;
    }

    public static OnClickListener lambdaFactory$(BookingEditTextFragment bookingEditTextFragment) {
        return new BookingEditTextFragment$$Lambda$2(bookingEditTextFragment);
    }

    public void onClick(View view) {
        this.arg$1.onClickNext();
    }
}
