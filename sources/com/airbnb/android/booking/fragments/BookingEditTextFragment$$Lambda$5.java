package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingEditTextFragment$$Lambda$5 implements OnClickListener {
    private final BookingEditTextFragment arg$1;

    private BookingEditTextFragment$$Lambda$5(BookingEditTextFragment bookingEditTextFragment) {
        this.arg$1 = bookingEditTextFragment;
    }

    public static OnClickListener lambdaFactory$(BookingEditTextFragment bookingEditTextFragment) {
        return new BookingEditTextFragment$$Lambda$5(bookingEditTextFragment);
    }

    public void onClick(View view) {
        this.arg$1.showKeyboard();
    }
}
