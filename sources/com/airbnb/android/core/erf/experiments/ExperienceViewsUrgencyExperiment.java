package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_urgency_message")
public class ExperienceViewsUrgencyExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }

    public boolean isPhoneOnly() {
        return true;
    }
}
