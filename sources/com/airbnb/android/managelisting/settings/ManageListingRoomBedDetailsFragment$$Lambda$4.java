package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingRoomBedDetailsFragment$$Lambda$4 implements Action1 {
    private final ManageListingRoomBedDetailsFragment arg$1;

    private ManageListingRoomBedDetailsFragment$$Lambda$4(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment) {
        this.arg$1 = manageListingRoomBedDetailsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment) {
        return new ManageListingRoomBedDetailsFragment$$Lambda$4(manageListingRoomBedDetailsFragment);
    }

    public void call(Object obj) {
        ManageListingRoomBedDetailsFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
