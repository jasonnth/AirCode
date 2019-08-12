package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "reorder")
public class PromoteProfileLanguageOrderExperiment extends ExperimentConfig {
    public boolean shouldForceTreatment() {
        return BuildHelper.isDebugFeaturesEnabled();
    }

    public boolean isEnabled() {
        return true;
    }
}
