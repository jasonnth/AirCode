package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;

public class MTPostHomeBookingExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }

    public boolean isPhoneOnly() {
        return true;
    }

    public boolean shouldForceTreatment() {
        return true;
    }

    public String treatmentToForce() {
        return "non_editorial";
    }
}
