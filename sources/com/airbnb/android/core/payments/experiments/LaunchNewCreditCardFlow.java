package com.airbnb.android.core.payments.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "launch_new_credit_card_flow")
public class LaunchNewCreditCardFlow extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
