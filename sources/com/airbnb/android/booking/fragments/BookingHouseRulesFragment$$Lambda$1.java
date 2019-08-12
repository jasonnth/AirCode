package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class BookingHouseRulesFragment$$Lambda$1 implements Action1 {
    private final BookingHouseRulesFragment arg$1;

    private BookingHouseRulesFragment$$Lambda$1(BookingHouseRulesFragment bookingHouseRulesFragment) {
        this.arg$1 = bookingHouseRulesFragment;
    }

    public static Action1 lambdaFactory$(BookingHouseRulesFragment bookingHouseRulesFragment) {
        return new BookingHouseRulesFragment$$Lambda$1(bookingHouseRulesFragment);
    }

    public void call(Object obj) {
        this.arg$1.houseRulesEpoxyController.setTranslatedHouseRules(((ListingResponse) obj).listing.getLocalizedAdditionalHouseRulesWithGoogleTranslate());
    }
}
