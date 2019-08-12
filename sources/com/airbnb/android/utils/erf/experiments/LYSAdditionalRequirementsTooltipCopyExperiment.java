package com.airbnb.android.utils.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class LYSAdditionalRequirementsTooltipCopyExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
