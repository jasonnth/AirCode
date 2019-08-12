package com.airbnb.android.core.businesstravel.experiments;

import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class BusinessTravelWorkEmailTextExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return Trebuchet.launch(TrebuchetKeys.BT_WORK_EMAIL_PROMO_TEXT, false);
    }
}
