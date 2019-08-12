package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_share_sheet")
public class ScreenshotShareExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }

    public boolean shouldForceTreatment() {
        return BuildHelper.isFuture();
    }
}
