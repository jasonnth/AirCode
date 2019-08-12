package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_map_card_on_top")
public class P2ShowMapCardExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return false;
    }
}
