package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "track_visual_component_display")
public class VisualComponentDisplayExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }

    public boolean shouldForceTreatment() {
        return BuildHelper.isDevelopmentBuild();
    }

    public boolean shouldUseStickyAssignments() {
        return true;
    }
}
