package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_host_referrals")
public class HostReferralsExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return Trebuchet.launch("host_referrals", Trebuchet.KEY_ANDROID_ENABLED, false);
    }
}
