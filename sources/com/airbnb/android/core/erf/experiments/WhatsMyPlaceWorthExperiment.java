package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_before_lys_from_menu")
public class WhatsMyPlaceWorthExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
