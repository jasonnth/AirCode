package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "use_mitek_for_id")
public class IdentityVerificationFlowWithMitekExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }

    public boolean shouldUseStickyAssignments() {
        return true;
    }
}
