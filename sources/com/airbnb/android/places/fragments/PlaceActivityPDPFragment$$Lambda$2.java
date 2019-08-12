package com.airbnb.android.places.fragments;

import com.airbnb.android.places.C7627R;
import p032rx.functions.Action1;

final /* synthetic */ class PlaceActivityPDPFragment$$Lambda$2 implements Action1 {
    private final PlaceActivityPDPFragment arg$1;

    private PlaceActivityPDPFragment$$Lambda$2(PlaceActivityPDPFragment placeActivityPDPFragment) {
        this.arg$1 = placeActivityPDPFragment;
    }

    public static Action1 lambdaFactory$(PlaceActivityPDPFragment placeActivityPDPFragment) {
        return new PlaceActivityPDPFragment$$Lambda$2(placeActivityPDPFragment);
    }

    public void call(Object obj) {
        this.arg$1.showErrorSnackbar(C7627R.string.error, PlaceActivityPDPFragment$$Lambda$18.lambdaFactory$(this.arg$1));
    }
}
