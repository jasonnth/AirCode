package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "should_see_family_friendly_amenities")
public class ManageListingFamilyAmenitiesExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
