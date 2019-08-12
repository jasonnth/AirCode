package com.airbnb.android.listing.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class WhatsMyPlaceWorthFragment$$Lambda$4 implements Action1 {
    private final WhatsMyPlaceWorthFragment arg$1;

    private WhatsMyPlaceWorthFragment$$Lambda$4(WhatsMyPlaceWorthFragment whatsMyPlaceWorthFragment) {
        this.arg$1 = whatsMyPlaceWorthFragment;
    }

    public static Action1 lambdaFactory$(WhatsMyPlaceWorthFragment whatsMyPlaceWorthFragment) {
        return new WhatsMyPlaceWorthFragment$$Lambda$4(whatsMyPlaceWorthFragment);
    }

    public void call(Object obj) {
        this.arg$1.showCreateListingError((AirRequestNetworkException) obj);
    }
}
