package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "use_property_type_and_space_type_in_p2_home_card_subtitle")
public class P2ShowSpaceTypeOnHomeCardExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
