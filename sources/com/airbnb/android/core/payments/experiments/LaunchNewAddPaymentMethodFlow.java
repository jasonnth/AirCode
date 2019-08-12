package com.airbnb.android.core.payments.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "launch_new_add_payment_method_flow")
public class LaunchNewAddPaymentMethodFlow extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
