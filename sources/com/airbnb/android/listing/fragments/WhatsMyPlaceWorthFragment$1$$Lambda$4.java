package com.airbnb.android.listing.fragments;

import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class WhatsMyPlaceWorthFragment$1$$Lambda$4 implements Listener {
    private final C72411 arg$1;

    private WhatsMyPlaceWorthFragment$1$$Lambda$4(C72411 r1) {
        this.arg$1 = r1;
    }

    public static Listener lambdaFactory$(C72411 r1) {
        return new WhatsMyPlaceWorthFragment$1$$Lambda$4(r1);
    }

    public void itemSelected(Object obj) {
        C72411.lambda$placeTypeRequested$3(this.arg$1, (SpaceType) obj);
    }
}
