package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "should_show_location_amenities")
public class ManageListingLocationAmenitiesExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
