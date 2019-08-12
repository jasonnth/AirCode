package com.airbnb.android.core.businesstravel.experiments;

import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "p4_checkbox")
public class BusinessTravelNonVUExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return Trebuchet.launch(TrebuchetKeys.BT_INTERSTITIAL_NONVU, false);
    }
}
