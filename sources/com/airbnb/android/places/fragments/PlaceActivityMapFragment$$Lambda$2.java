package com.airbnb.android.places.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PlaceActivityMapFragment$$Lambda$2 implements OnClickListener {
    private final PlaceActivityMapFragment arg$1;

    private PlaceActivityMapFragment$$Lambda$2(PlaceActivityMapFragment placeActivityMapFragment) {
        this.arg$1 = placeActivityMapFragment;
    }

    public static OnClickListener lambdaFactory$(PlaceActivityMapFragment placeActivityMapFragment) {
        return new PlaceActivityMapFragment$$Lambda$2(placeActivityMapFragment);
    }

    public void onClick(View view) {
        this.arg$1.activityController.showDirections(this.arg$1.place);
    }
}
