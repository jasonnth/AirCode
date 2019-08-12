package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.enums.BedDetailType;
import com.google.common.base.Predicate;

final /* synthetic */ class SingleRoomBedDetailsController$$Lambda$5 implements Predicate {
    private final SingleRoomBedDetailsController arg$1;

    private SingleRoomBedDetailsController$$Lambda$5(SingleRoomBedDetailsController singleRoomBedDetailsController) {
        this.arg$1 = singleRoomBedDetailsController;
    }

    public static Predicate lambdaFactory$(SingleRoomBedDetailsController singleRoomBedDetailsController) {
        return new SingleRoomBedDetailsController$$Lambda$5(singleRoomBedDetailsController);
    }

    public boolean apply(Object obj) {
        return SingleRoomBedDetailsController.lambda$getNonemptyBeds$3(this.arg$1, (BedDetailType) obj);
    }
}
