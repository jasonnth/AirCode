package com.airbnb.android.core.businesstravel.experiments;

import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.ExperimentConfig;

public class BusinessTravelProfileExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return Trebuchet.launch(TrebuchetKeys.ENABLE_TRAVEL_FOR_WORK_ON_PROFILE, false);
    }
}
