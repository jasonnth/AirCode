package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.BedDetailType;
import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;

final /* synthetic */ class SingleRoomBedDetailsController$$Lambda$2 implements OnValueChangedListener {
    private final SingleRoomBedDetailsController arg$1;
    private final BedDetailType arg$2;

    private SingleRoomBedDetailsController$$Lambda$2(SingleRoomBedDetailsController singleRoomBedDetailsController, BedDetailType bedDetailType) {
        this.arg$1 = singleRoomBedDetailsController;
        this.arg$2 = bedDetailType;
    }

    public static OnValueChangedListener lambdaFactory$(SingleRoomBedDetailsController singleRoomBedDetailsController, BedDetailType bedDetailType) {
        return new SingleRoomBedDetailsController$$Lambda$2(singleRoomBedDetailsController, bedDetailType);
    }

    public void onValueChanged(int i, int i2) {
        SingleRoomBedDetailsController.lambda$addStepperRow$1(this.arg$1, this.arg$2, i, i2);
    }
}
