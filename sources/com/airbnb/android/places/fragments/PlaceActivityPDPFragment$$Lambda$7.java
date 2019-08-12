package com.airbnb.android.places.fragments;

import com.airbnb.android.places.responses.PlaceReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PlaceActivityPDPFragment$$Lambda$7 implements Action1 {
    private final PlaceActivityPDPFragment arg$1;

    private PlaceActivityPDPFragment$$Lambda$7(PlaceActivityPDPFragment placeActivityPDPFragment) {
        this.arg$1 = placeActivityPDPFragment;
    }

    public static Action1 lambdaFactory$(PlaceActivityPDPFragment placeActivityPDPFragment) {
        return new PlaceActivityPDPFragment$$Lambda$7(placeActivityPDPFragment);
    }

    public void call(Object obj) {
        PlaceActivityPDPFragment.lambda$new$11(this.arg$1, (PlaceReservationResponse) obj);
    }
}
