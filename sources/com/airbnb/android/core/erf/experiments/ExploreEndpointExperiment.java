package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "use_new_format")
public class ExploreEndpointExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
