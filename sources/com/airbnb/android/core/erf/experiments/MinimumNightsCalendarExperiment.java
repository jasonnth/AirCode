package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_improved_min_nights_calendar")
public class MinimumNightsCalendarExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return BuildHelper.isDevelopmentBuild();
    }
}
