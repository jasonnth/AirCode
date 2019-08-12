package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.ListingRoomsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSRoomBedDetailsFragment$$Lambda$5 implements Action1 {
    private final LYSRoomBedDetailsFragment arg$1;

    private LYSRoomBedDetailsFragment$$Lambda$5(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment) {
        this.arg$1 = lYSRoomBedDetailsFragment;
    }

    public static Action1 lambdaFactory$(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment) {
        return new LYSRoomBedDetailsFragment$$Lambda$5(lYSRoomBedDetailsFragment);
    }

    public void call(Object obj) {
        LYSRoomBedDetailsFragment.lambda$new$4(this.arg$1, (ListingRoomsResponse) obj);
    }
}
