package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingRoomBedDetailsFragment$$Lambda$6 implements Action1 {
    private final ManageListingRoomBedDetailsFragment arg$1;

    private ManageListingRoomBedDetailsFragment$$Lambda$6(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment) {
        this.arg$1 = manageListingRoomBedDetailsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingRoomBedDetailsFragment manageListingRoomBedDetailsFragment) {
        return new ManageListingRoomBedDetailsFragment$$Lambda$6(manageListingRoomBedDetailsFragment);
    }

    public void call(Object obj) {
        ManageListingRoomBedDetailsFragment.lambda$new$6(this.arg$1, (AirRequestNetworkException) obj);
    }
}
