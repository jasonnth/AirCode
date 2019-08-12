package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingSummaryFragment$$Lambda$5 implements OnClickListener {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$5(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static OnClickListener lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$5(bookingSummaryFragment);
    }

    public void onClick(View view) {
        BookingSummaryFragment.lambda$new$9(this.arg$1, view);
    }
}
