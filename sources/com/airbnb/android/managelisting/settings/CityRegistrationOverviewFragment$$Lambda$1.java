package com.airbnb.android.managelisting.settings;

import com.airbnb.android.listing.adapters.CityRegistrationOverviewAdapter.Listener;

final /* synthetic */ class CityRegistrationOverviewFragment$$Lambda$1 implements Listener {
    private final ManageListingActionExecutor arg$1;

    private CityRegistrationOverviewFragment$$Lambda$1(ManageListingActionExecutor manageListingActionExecutor) {
        this.arg$1 = manageListingActionExecutor;
    }

    public static Listener lambdaFactory$(ManageListingActionExecutor manageListingActionExecutor) {
        return new CityRegistrationOverviewFragment$$Lambda$1(manageListingActionExecutor);
    }

    public void addExistingLicense() {
        this.arg$1.cityRegistrationExemption();
    }
}
