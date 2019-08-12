package com.airbnb.android.managelisting.settings;

import com.airbnb.android.listing.adapters.TripLengthSettingsHelper.Listener;

final /* synthetic */ class ManageListingTripLengthAdapter$$Lambda$2 implements Listener {
    private final ManageListingTripLengthAdapter arg$1;

    private ManageListingTripLengthAdapter$$Lambda$2(ManageListingTripLengthAdapter manageListingTripLengthAdapter) {
        this.arg$1 = manageListingTripLengthAdapter;
    }

    public static Listener lambdaFactory$(ManageListingTripLengthAdapter manageListingTripLengthAdapter) {
        return new ManageListingTripLengthAdapter$$Lambda$2(manageListingTripLengthAdapter);
    }

    public void modelsUpdated() {
        this.arg$1.notifyValidSettingsListener();
    }
}
