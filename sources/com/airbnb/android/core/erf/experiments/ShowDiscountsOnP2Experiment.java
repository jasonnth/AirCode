package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_discounts_on_p2")
public class ShowDiscountsOnP2Experiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
