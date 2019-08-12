package com.airbnb.android.places.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PlaceActivityPDPFragment$$Lambda$14 implements OnClickListener {
    private final PlaceActivityPDPFragment arg$1;
    private final long arg$2;

    private PlaceActivityPDPFragment$$Lambda$14(PlaceActivityPDPFragment placeActivityPDPFragment, long j) {
        this.arg$1 = placeActivityPDPFragment;
        this.arg$2 = j;
    }

    public static OnClickListener lambdaFactory$(PlaceActivityPDPFragment placeActivityPDPFragment, long j) {
        return new PlaceActivityPDPFragment$$Lambda$14(placeActivityPDPFragment, j);
    }

    public void onClick(View view) {
        this.arg$1.viewMeetupReservation(this.arg$2);
    }
}
