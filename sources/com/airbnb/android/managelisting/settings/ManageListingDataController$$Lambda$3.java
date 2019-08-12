package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.ListingRoom;
import com.google.common.base.Predicate;

final /* synthetic */ class ManageListingDataController$$Lambda$3 implements Predicate {
    private final int arg$1;

    private ManageListingDataController$$Lambda$3(int i) {
        this.arg$1 = i;
    }

    public static Predicate lambdaFactory$(int i) {
        return new ManageListingDataController$$Lambda$3(i);
    }

    public boolean apply(Object obj) {
        return ManageListingDataController.lambda$getRoomByNumber$2(this.arg$1, (ListingRoom) obj);
    }
}
