package com.airbnb.android.places.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PlaceActivityPDPFragment$$Lambda$13 implements OnClickListener {
    private final PlaceActivityPDPFragment arg$1;

    private PlaceActivityPDPFragment$$Lambda$13(PlaceActivityPDPFragment placeActivityPDPFragment) {
        this.arg$1 = placeActivityPDPFragment;
    }

    public static OnClickListener lambdaFactory$(PlaceActivityPDPFragment placeActivityPDPFragment) {
        return new PlaceActivityPDPFragment$$Lambda$13(placeActivityPDPFragment);
    }

    public void onClick(View view) {
        this.arg$1.joinMeetup();
    }
}