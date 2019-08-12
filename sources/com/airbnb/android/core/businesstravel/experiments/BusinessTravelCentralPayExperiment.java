package com.airbnb.android.core.businesstravel.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "allow_business_bookings")
public class BusinessTravelCentralPayExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
