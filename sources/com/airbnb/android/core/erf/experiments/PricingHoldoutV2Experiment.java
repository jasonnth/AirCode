package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "full_treatment")
public class PricingHoldoutV2Experiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
