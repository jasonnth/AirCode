package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "p4_redesign")
public class P4RedesignExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
