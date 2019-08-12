package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class ColdStartOptimizationExperiment extends ExperimentConfig {
    public static final String EXPERIMENT_NAME = "cold_start_optimization_android";
    public static final String TREATMENT_NAME = "treatment";

    public boolean isEnabled() {
        return true;
    }
}
