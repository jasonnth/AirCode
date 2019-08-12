package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class ManageListingRoomsGuestsFragment$$Lambda$3 implements Action1 {
    private final ManageListingRoomsGuestsFragment arg$1;

    private ManageListingRoomsGuestsFragment$$Lambda$3(ManageListingRoomsGuestsFragment manageListingRoomsGuestsFragment) {
        this.arg$1 = manageListingRoomsGuestsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingRoomsGuestsFragment manageListingRoomsGuestsFragment) {
        return new ManageListingRoomsGuestsFragment$$Lambda$3(manageListingRoomsGuestsFragment);
    }

    public void call(Object obj) {
        this.arg$1.adapter.setInputEnabled(true);
    }
}
