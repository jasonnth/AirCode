package com.airbnb.android.listing.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WhatsMyPlaceWorthFragment$$Lambda$5 implements OnClickListener {
    private final WhatsMyPlaceWorthFragment arg$1;

    private WhatsMyPlaceWorthFragment$$Lambda$5(WhatsMyPlaceWorthFragment whatsMyPlaceWorthFragment) {
        this.arg$1 = whatsMyPlaceWorthFragment;
    }

    public static OnClickListener lambdaFactory$(WhatsMyPlaceWorthFragment whatsMyPlaceWorthFragment) {
        return new WhatsMyPlaceWorthFragment$$Lambda$5(whatsMyPlaceWorthFragment);
    }

    public void onClick(View view) {
        this.arg$1.startListing();
    }
}
