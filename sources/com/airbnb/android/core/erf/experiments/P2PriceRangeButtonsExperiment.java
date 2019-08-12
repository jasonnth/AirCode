package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_new_price_range_buttons")
public class P2PriceRangeButtonsExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return false;
    }
}
