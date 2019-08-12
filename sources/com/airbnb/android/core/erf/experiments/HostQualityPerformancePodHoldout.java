package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class HostQualityPerformancePodHoldout extends ExperimentConfig {
    public static final String EXPERIMENT_NAME = "quality_2016_sep_performance_holdout";
    public static final String TREATMENT_NAME = "treatment";

    public boolean isEnabled() {
        return true;
    }

    public boolean shouldUseStickyAssignments() {
        return true;
    }
}
