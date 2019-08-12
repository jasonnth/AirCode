package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "identity_on_p4")
public class IdentityOnP4Experiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
