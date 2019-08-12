package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_new_insights")
public class NewMay2017InsightsExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
