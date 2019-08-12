package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.views.OptionsMenuFactory.Listener;

final /* synthetic */ class ManageListingFeesAdapter$$Lambda$7 implements Listener {
    private final ManageListingFeesAdapter arg$1;

    private ManageListingFeesAdapter$$Lambda$7(ManageListingFeesAdapter manageListingFeesAdapter) {
        this.arg$1 = manageListingFeesAdapter;
    }

    public static Listener lambdaFactory$(ManageListingFeesAdapter manageListingFeesAdapter) {
        return new ManageListingFeesAdapter$$Lambda$7(manageListingFeesAdapter);
    }

    public void itemSelected(Object obj) {
        ManageListingFeesAdapter.lambda$showGuestOptionsMenu$6(this.arg$1, (Integer) obj);
    }
}
