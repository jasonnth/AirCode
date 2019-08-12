package com.airbnb.android.core.payments.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_pay_u_flow")
public class ShowPayUFlow extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
