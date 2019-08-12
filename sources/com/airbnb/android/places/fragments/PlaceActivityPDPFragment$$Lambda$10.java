package com.airbnb.android.places.fragments;

import com.airbnb.android.places.ResyController.ResyUpdateListener;
import com.airbnb.android.places.ResyState;

final /* synthetic */ class PlaceActivityPDPFragment$$Lambda$10 implements ResyUpdateListener {
    private final PlaceActivityPDPFragment arg$1;

    private PlaceActivityPDPFragment$$Lambda$10(PlaceActivityPDPFragment placeActivityPDPFragment) {
        this.arg$1 = placeActivityPDPFragment;
    }

    public static ResyUpdateListener lambdaFactory$(PlaceActivityPDPFragment placeActivityPDPFragment) {
        return new PlaceActivityPDPFragment$$Lambda$10(placeActivityPDPFragment);
    }

    public void onContentUpdated(ResyState resyState) {
        this.arg$1.controller.setData(this.arg$1.activityModel, resyState);
    }
}
