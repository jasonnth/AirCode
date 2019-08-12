package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LYSRoomBedDetailsFragment$$Lambda$2 implements Action1 {
    private final LYSRoomBedDetailsFragment arg$1;

    private LYSRoomBedDetailsFragment$$Lambda$2(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment) {
        this.arg$1 = lYSRoomBedDetailsFragment;
    }

    public static Action1 lambdaFactory$(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment) {
        return new LYSRoomBedDetailsFragment$$Lambda$2(lYSRoomBedDetailsFragment);
    }

    public void call(Object obj) {
        LYSRoomBedDetailsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
