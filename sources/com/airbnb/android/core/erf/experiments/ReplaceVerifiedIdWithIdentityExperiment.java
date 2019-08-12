package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "replace_verified_id_with_identity")
public class ReplaceVerifiedIdWithIdentityExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }

    public boolean shouldUseStickyAssignments() {
        return true;
    }
}
