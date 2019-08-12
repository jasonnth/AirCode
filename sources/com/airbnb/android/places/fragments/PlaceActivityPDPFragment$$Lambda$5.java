package com.airbnb.android.places.fragments;

import com.airbnb.android.places.responses.ActivityReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PlaceActivityPDPFragment$$Lambda$5 implements Action1 {
    private final PlaceActivityPDPFragment arg$1;

    private PlaceActivityPDPFragment$$Lambda$5(PlaceActivityPDPFragment placeActivityPDPFragment) {
        this.arg$1 = placeActivityPDPFragment;
    }

    public static Action1 lambdaFactory$(PlaceActivityPDPFragment placeActivityPDPFragment) {
        return new PlaceActivityPDPFragment$$Lambda$5(placeActivityPDPFragment);
    }

    public void call(Object obj) {
        PlaceActivityPDPFragment.lambda$new$8(this.arg$1, (ActivityReservationResponse) obj);
    }
}
