package com.airbnb.android.listing.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WhatsMyPlaceWorthFragment$$Lambda$3 implements Action1 {
    private final WhatsMyPlaceWorthFragment arg$1;

    private WhatsMyPlaceWorthFragment$$Lambda$3(WhatsMyPlaceWorthFragment whatsMyPlaceWorthFragment) {
        this.arg$1 = whatsMyPlaceWorthFragment;
    }

    public static Action1 lambdaFactory$(WhatsMyPlaceWorthFragment whatsMyPlaceWorthFragment) {
        return new WhatsMyPlaceWorthFragment$$Lambda$3(whatsMyPlaceWorthFragment);
    }

    public void call(Object obj) {
        this.arg$1.transitionToListYourSpace(((SimpleListingResponse) obj).listing.getId(), "get_started_with_listing");
    }
}
