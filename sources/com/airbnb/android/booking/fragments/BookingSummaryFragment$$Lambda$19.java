package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingSummaryFragment$$Lambda$19 implements OnClickListener {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$19(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static OnClickListener lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$19(bookingSummaryFragment);
    }

    public void onClick(View view) {
        this.arg$1.clickEmailAddress();
    }
}