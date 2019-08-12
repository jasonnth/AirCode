package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "use_airbnb_for_selfie")
public class IdentityVerificationSelfieWithAirbnbExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
