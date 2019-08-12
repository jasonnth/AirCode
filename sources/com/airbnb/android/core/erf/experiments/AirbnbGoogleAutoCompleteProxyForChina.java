package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class AirbnbGoogleAutoCompleteProxyForChina extends ExperimentConfig {
    public boolean isEnabled() {
        return AppLaunchUtils.isUserInChina();
    }
}
