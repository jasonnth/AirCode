package com.airbnb.android.places.fragments;

import com.airbnb.android.places.responses.MeetupActivityResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PlaceActivityPDPFragment$$Lambda$3 implements Action1 {
    private final PlaceActivityPDPFragment arg$1;

    private PlaceActivityPDPFragment$$Lambda$3(PlaceActivityPDPFragment placeActivityPDPFragment) {
        this.arg$1 = placeActivityPDPFragment;
    }

    public static Action1 lambdaFactory$(PlaceActivityPDPFragment placeActivityPDPFragment) {
        return new PlaceActivityPDPFragment$$Lambda$3(placeActivityPDPFragment);
    }

    public void call(Object obj) {
        PlaceActivityPDPFragment.lambda$new$5(this.arg$1, (MeetupActivityResponse) obj);
    }
}
