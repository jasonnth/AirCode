package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "enable_android_add_to_plans")
public class PlacesPickAddToPlans extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
