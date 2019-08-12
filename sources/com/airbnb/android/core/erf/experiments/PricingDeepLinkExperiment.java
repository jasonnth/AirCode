package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "use_dld")
public class PricingDeepLinkExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
