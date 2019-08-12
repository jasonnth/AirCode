package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingSummaryFragment$$Lambda$21 implements OnClickListener {
    private final BookingSummaryFragment arg$1;
    private final boolean arg$2;

    private BookingSummaryFragment$$Lambda$21(BookingSummaryFragment bookingSummaryFragment, boolean z) {
        this.arg$1 = bookingSummaryFragment;
        this.arg$2 = z;
    }

    public static OnClickListener lambdaFactory$(BookingSummaryFragment bookingSummaryFragment, boolean z) {
        return new BookingSummaryFragment$$Lambda$21(bookingSummaryFragment, z);
    }

    public void onClick(View view) {
        BookingSummaryFragment.lambda$showVerificationSnackbar$16(this.arg$1, this.arg$2, view);
    }
}
