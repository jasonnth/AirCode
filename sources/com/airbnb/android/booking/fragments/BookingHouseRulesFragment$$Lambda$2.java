package com.airbnb.android.booking.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class BookingHouseRulesFragment$$Lambda$2 implements Action1 {
    private final BookingHouseRulesFragment arg$1;

    private BookingHouseRulesFragment$$Lambda$2(BookingHouseRulesFragment bookingHouseRulesFragment) {
        this.arg$1 = bookingHouseRulesFragment;
    }

    public static Action1 lambdaFactory$(BookingHouseRulesFragment bookingHouseRulesFragment) {
        return new BookingHouseRulesFragment$$Lambda$2(bookingHouseRulesFragment);
    }

    public void call(Object obj) {
        BookingHouseRulesFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
