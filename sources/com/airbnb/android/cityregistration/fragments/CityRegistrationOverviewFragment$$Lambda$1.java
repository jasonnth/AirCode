package com.airbnb.android.cityregistration.fragments;

import com.airbnb.android.cityregistration.adapters.CityRegistrationOverviewAdapter.Listener;
import com.airbnb.android.cityregistration.executor.CityRegistrationActionExecutor;

final /* synthetic */ class CityRegistrationOverviewFragment$$Lambda$1 implements Listener {
    private final CityRegistrationActionExecutor arg$1;

    private CityRegistrationOverviewFragment$$Lambda$1(CityRegistrationActionExecutor cityRegistrationActionExecutor) {
        this.arg$1 = cityRegistrationActionExecutor;
    }

    public static Listener lambdaFactory$(CityRegistrationActionExecutor cityRegistrationActionExecutor) {
        return new CityRegistrationOverviewFragment$$Lambda$1(cityRegistrationActionExecutor);
    }

    public void addExistingLicense() {
        this.arg$1.cityRegistrationExemption();
    }
}
