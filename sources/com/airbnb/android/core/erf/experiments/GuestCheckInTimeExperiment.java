package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class GuestCheckInTimeExperiment extends ExperimentConfig {
    public boolean shouldForceTreatment() {
        return BuildHelper.isFuture();
    }

    public boolean isEnabled() {
        return true;
    }
}
