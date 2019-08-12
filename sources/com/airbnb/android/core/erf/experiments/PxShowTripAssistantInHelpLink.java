package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "link_to_trip_assistant_on")
public class PxShowTripAssistantInHelpLink extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
