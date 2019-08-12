package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingReviewFragment$$Lambda$1 implements OnClickListener {
    private final BookingReviewFragment arg$1;

    private BookingReviewFragment$$Lambda$1(BookingReviewFragment bookingReviewFragment) {
        this.arg$1 = bookingReviewFragment;
    }

    public static OnClickListener lambdaFactory$(BookingReviewFragment bookingReviewFragment) {
        return new BookingReviewFragment$$Lambda$1(bookingReviewFragment);
    }

    public void onClick(View view) {
        BookingReviewFragment.lambda$new$0(this.arg$1, view);
    }
}
