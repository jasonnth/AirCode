package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "enable")
public class ChinaDomainExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
