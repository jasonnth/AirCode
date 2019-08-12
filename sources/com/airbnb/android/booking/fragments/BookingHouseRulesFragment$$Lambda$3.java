package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BookingHouseRulesFragment$$Lambda$3 implements OnClickListener {
    private final BookingHouseRulesFragment arg$1;

    private BookingHouseRulesFragment$$Lambda$3(BookingHouseRulesFragment bookingHouseRulesFragment) {
        this.arg$1 = bookingHouseRulesFragment;
    }

    public static OnClickListener lambdaFactory$(BookingHouseRulesFragment bookingHouseRulesFragment) {
        return new BookingHouseRulesFragment$$Lambda$3(bookingHouseRulesFragment);
    }

    public void onClick(View view) {
        this.arg$1.fetchTranslation();
    }
}
