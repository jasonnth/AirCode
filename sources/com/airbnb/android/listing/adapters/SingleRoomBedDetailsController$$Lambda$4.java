package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.BedDetailType;
import com.google.common.base.Function;

final /* synthetic */ class SingleRoomBedDetailsController$$Lambda$4 implements Function {
    private final SingleRoomBedDetailsController arg$1;

    private SingleRoomBedDetailsController$$Lambda$4(SingleRoomBedDetailsController singleRoomBedDetailsController) {
        this.arg$1 = singleRoomBedDetailsController;
    }

    public static Function lambdaFactory$(SingleRoomBedDetailsController singleRoomBedDetailsController) {
        return new SingleRoomBedDetailsController$$Lambda$4(singleRoomBedDetailsController);
    }

    public Object apply(Object obj) {
        return this.arg$1.bedTypeFromEnum((BedDetailType) obj);
    }
}
