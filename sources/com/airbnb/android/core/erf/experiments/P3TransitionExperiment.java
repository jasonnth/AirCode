package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "use_new_transition")
public class P3TransitionExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
