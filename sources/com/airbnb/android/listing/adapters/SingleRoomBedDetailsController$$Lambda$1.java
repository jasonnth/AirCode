package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SingleRoomBedDetailsController$$Lambda$1 implements OnClickListener {
    private final SingleRoomBedDetailsController arg$1;

    private SingleRoomBedDetailsController$$Lambda$1(SingleRoomBedDetailsController singleRoomBedDetailsController) {
        this.arg$1 = singleRoomBedDetailsController;
    }

    public static OnClickListener lambdaFactory$(SingleRoomBedDetailsController singleRoomBedDetailsController) {
        return new SingleRoomBedDetailsController$$Lambda$1(singleRoomBedDetailsController);
    }

    public void onClick(View view) {
        SingleRoomBedDetailsController.lambda$buildModels$0(this.arg$1, view);
    }
}
