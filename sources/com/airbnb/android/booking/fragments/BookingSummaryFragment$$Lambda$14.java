package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingSummaryFragment$$Lambda$14 implements OnClickListener {
    private final BookingSummaryFragment arg$1;

    private BookingSummaryFragment$$Lambda$14(BookingSummaryFragment bookingSummaryFragment) {
        this.arg$1 = bookingSummaryFragment;
    }

    public static OnClickListener lambdaFactory$(BookingSummaryFragment bookingSummaryFragment) {
        return new BookingSummaryFragment$$Lambda$14(bookingSummaryFragment);
    }

    public void onClick(View view) {
        this.arg$1.clickGovernmentId("confirm_gov_id_booking_step_snackbar");
    }
}
