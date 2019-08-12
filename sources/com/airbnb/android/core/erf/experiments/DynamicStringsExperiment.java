package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "fetch_and_use_dynamic_strings")
public class DynamicStringsExperiment extends ExperimentConfig {
    public boolean shouldForceTreatment() {
        return DebugSettings.ENABLE_DYNAMIC_STRINGS.isEnabled();
    }

    public boolean isEnabled() {
        return BuildHelper.isFuture();
    }
}
