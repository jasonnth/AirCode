package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;

public class ChinaStoriesHoldout extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }

    public boolean shouldUseStickyAssignments() {
        return true;
    }
}
