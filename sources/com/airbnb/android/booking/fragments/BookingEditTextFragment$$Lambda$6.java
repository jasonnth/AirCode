package com.airbnb.android.booking.fragments;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class BookingEditTextFragment$$Lambda$6 implements Runnable {
    private final BookingEditTextFragment arg$1;

    private BookingEditTextFragment$$Lambda$6(BookingEditTextFragment bookingEditTextFragment) {
        this.arg$1 = bookingEditTextFragment;
    }

    public static Runnable lambdaFactory$(BookingEditTextFragment bookingEditTextFragment) {
        return new BookingEditTextFragment$$Lambda$6(bookingEditTextFragment);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.editTextView);
    }
}
