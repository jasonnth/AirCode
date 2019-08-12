package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class ManageListingRoomBedDetailsFragment$$Lambda$3 implements Action1 {
    private final ManageListingRoomBedDetailsFragment arg$1;

    private ManageListingRoomBedDetailsFragment$$Lambda$3(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment) {
        this.arg$1 = manageListingRoomBedDetailsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment) {
        return new ManageListingRoomBedDetailsFragment$$Lambda$3(manageListingRoomBedDetailsFragment);
    }

    public void call(Object obj) {
        this.arg$1.fetchRoomsData();
    }
}
