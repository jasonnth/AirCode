package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_use_tip")
public class PriceTipCopyExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
