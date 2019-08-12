package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingReviewFragment$2$$Lambda$1 implements OnClickListener {
    private final C55632 arg$1;

    private BookingReviewFragment$2$$Lambda$1(C55632 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C55632 r1) {
        return new BookingReviewFragment$2$$Lambda$1(r1);
    }

    public void onClick(View view) {
        BookingReviewFragment.this.updateDates();
    }
}
