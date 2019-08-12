package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingReviewFragment$1$$Lambda$1 implements OnClickListener {
    private final C55621 arg$1;

    private BookingReviewFragment$1$$Lambda$1(C55621 r1) {
        this.arg$1 = r1;
    }

    public static OnClickListener lambdaFactory$(C55621 r1) {
        return new BookingReviewFragment$1$$Lambda$1(r1);
    }

    public void onClick(View view) {
        BookingReviewFragment.this.updateBusinessTravelDetails();
    }
}
