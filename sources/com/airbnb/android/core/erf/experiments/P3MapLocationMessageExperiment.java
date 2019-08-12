package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_exact_location_disclaimer_on_p3_map")
public class P3MapLocationMessageExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
