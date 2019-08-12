package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_bold_p4_price_breakdown_credits")
public class BoldP4PriceBreakdownCreditsExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
