package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.ListingRoomsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingRoomBedDetailsFragment$$Lambda$5 implements Action1 {
    private final ManageListingRoomBedDetailsFragment arg$1;

    private ManageListingRoomBedDetailsFragment$$Lambda$5(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment) {
        this.arg$1 = manageListingRoomBedDetailsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment) {
        return new ManageListingRoomBedDetailsFragment$$Lambda$5(manageListingRoomBedDetailsFragment);
    }

    public void call(Object obj) {
        ManageListingRoomBedDetailsFragment.lambda$new$4(this.arg$1, (ListingRoomsResponse) obj);
    }
}
