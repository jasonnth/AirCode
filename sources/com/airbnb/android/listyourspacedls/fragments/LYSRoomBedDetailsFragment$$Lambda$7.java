package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSRoomBedDetailsFragment$$Lambda$7 implements OnClickListener {
    private final LYSRoomBedDetailsFragment arg$1;

    private LYSRoomBedDetailsFragment$$Lambda$7(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment) {
        this.arg$1 = lYSRoomBedDetailsFragment;
    }

    public static OnClickListener lambdaFactory$(LYSRoomBedDetailsFragment lYSRoomBedDetailsFragment) {
        return new LYSRoomBedDetailsFragment$$Lambda$7(lYSRoomBedDetailsFragment);
    }

    public void onClick(View view) {
        this.arg$1.fetchRoomsData();
    }
}
