package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "send_log_immediately")
public class LogImmediatelyExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
