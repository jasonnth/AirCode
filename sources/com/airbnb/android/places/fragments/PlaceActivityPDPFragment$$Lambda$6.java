package com.airbnb.android.places.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PlaceActivityPDPFragment$$Lambda$6 implements Action1 {
    private final PlaceActivityPDPFragment arg$1;

    private PlaceActivityPDPFragment$$Lambda$6(PlaceActivityPDPFragment placeActivityPDPFragment) {
        this.arg$1 = placeActivityPDPFragment;
    }

    public static Action1 lambdaFactory$(PlaceActivityPDPFragment placeActivityPDPFragment) {
        return new PlaceActivityPDPFragment$$Lambda$6(placeActivityPDPFragment);
    }

    public void call(Object obj) {
        PlaceActivityPDPFragment.lambda$new$10(this.arg$1, (AirRequestNetworkException) obj);
    }
}
