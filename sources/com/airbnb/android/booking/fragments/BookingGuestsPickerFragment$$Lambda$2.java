package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingGuestsPickerFragment$$Lambda$2 implements OnClickListener {
    private final BookingGuestsPickerFragment arg$1;

    private BookingGuestsPickerFragment$$Lambda$2(BookingGuestsPickerFragment bookingGuestsPickerFragment) {
        this.arg$1 = bookingGuestsPickerFragment;
    }

    public static OnClickListener lambdaFactory$(BookingGuestsPickerFragment bookingGuestsPickerFragment) {
        return new BookingGuestsPickerFragment$$Lambda$2(bookingGuestsPickerFragment);
    }

    public void onClick(View view) {
        BookingGuestsPickerFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
