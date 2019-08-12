package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingRoomsGuestsFragment$$Lambda$1 implements Action1 {
    private final ManageListingRoomsGuestsFragment arg$1;

    private ManageListingRoomsGuestsFragment$$Lambda$1(ManageListingRoomsGuestsFragment manageListingRoomsGuestsFragment) {
        this.arg$1 = manageListingRoomsGuestsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingRoomsGuestsFragment manageListingRoomsGuestsFragment) {
        return new ManageListingRoomsGuestsFragment$$Lambda$1(manageListingRoomsGuestsFragment);
    }

    public void call(Object obj) {
        ManageListingRoomsGuestsFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
