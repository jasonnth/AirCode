package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class P3BTRSummaryHighlightExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
