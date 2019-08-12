package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingRoomsGuestsFragment$$Lambda$2 implements Action1 {
    private final ManageListingRoomsGuestsFragment arg$1;

    private ManageListingRoomsGuestsFragment$$Lambda$2(ManageListingRoomsGuestsFragment manageListingRoomsGuestsFragment) {
        this.arg$1 = manageListingRoomsGuestsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingRoomsGuestsFragment manageListingRoomsGuestsFragment) {
        return new ManageListingRoomsGuestsFragment$$Lambda$2(manageListingRoomsGuestsFragment);
    }

    public void call(Object obj) {
        ManageListingRoomsGuestsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
