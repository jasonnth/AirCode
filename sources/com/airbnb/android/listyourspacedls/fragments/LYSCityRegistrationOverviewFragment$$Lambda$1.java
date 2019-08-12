package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.listing.adapters.CityRegistrationOverviewAdapter.Listener;
import com.airbnb.android.listyourspacedls.LYSDataController;

final /* synthetic */ class LYSCityRegistrationOverviewFragment$$Lambda$1 implements Listener {
    private final LYSDataController arg$1;

    private LYSCityRegistrationOverviewFragment$$Lambda$1(LYSDataController lYSDataController) {
        this.arg$1 = lYSDataController;
    }

    public static Listener lambdaFactory$(LYSDataController lYSDataController) {
        return new LYSCityRegistrationOverviewFragment$$Lambda$1(lYSDataController);
    }

    public void addExistingLicense() {
        this.arg$1.showCityRegistrationExemption();
    }
}
