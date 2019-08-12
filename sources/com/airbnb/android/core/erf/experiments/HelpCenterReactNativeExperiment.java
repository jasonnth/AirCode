package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "react_native")
public class HelpCenterReactNativeExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
