package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.BedDetailType;
import com.google.common.base.Predicate;

final /* synthetic */ class SingleRoomBedDetailsController$$Lambda$3 implements Predicate {
    private final SingleRoomBedDetailsController arg$1;

    private SingleRoomBedDetailsController$$Lambda$3(SingleRoomBedDetailsController singleRoomBedDetailsController) {
        this.arg$1 = singleRoomBedDetailsController;
    }

    public static Predicate lambdaFactory$(SingleRoomBedDetailsController singleRoomBedDetailsController) {
        return new SingleRoomBedDetailsController$$Lambda$3(singleRoomBedDetailsController);
    }

    public boolean apply(Object obj) {
        return SingleRoomBedDetailsController.lambda$getChanges$2(this.arg$1, (BedDetailType) obj);
    }
}
