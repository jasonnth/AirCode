package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class AndroidSkipPaymentOptionsFetchOnP4 extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
