package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSRoomBedDetailsFragment$$Lambda$3 implements Action1 {
    private final LYSRoomBedDetailsFragment arg$1;

    private LYSRoomBedDetailsFragment$$Lambda$3(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment) {
        this.arg$1 = lYSRoomBedDetailsFragment;
    }

    public static Action1 lambdaFactory$(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment) {
        return new LYSRoomBedDetailsFragment$$Lambda$3(lYSRoomBedDetailsFragment);
    }

    public void call(Object obj) {
        this.arg$1.fetchRoomsData();
    }
}
