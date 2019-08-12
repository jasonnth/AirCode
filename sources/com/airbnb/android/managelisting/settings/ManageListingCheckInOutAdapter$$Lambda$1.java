package com.airbnb.android.managelisting.settings;

import com.airbnb.android.listing.utils.CheckInOutSettingsHelper.Listener;

final /* synthetic */ class ManageListingCheckInOutAdapter$$Lambda$1 implements Listener {
    private final ManageListingCheckInOutAdapter arg$1;

    private ManageListingCheckInOutAdapter$$Lambda$1(ManageListingCheckInOutAdapter manageListingCheckInOutAdapter) {
        this.arg$1 = manageListingCheckInOutAdapter;
    }

    public static Listener lambdaFactory$(ManageListingCheckInOutAdapter manageListingCheckInOutAdapter) {
        return new ManageListingCheckInOutAdapter$$Lambda$1(manageListingCheckInOutAdapter);
    }

    public void modelsUpdated() {
        this.arg$1.notifyModelsChanged();
    }
}
