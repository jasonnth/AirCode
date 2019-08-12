package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "enable_android_related_activities_carousel")
public class PlacesRelatedItemsCarousel extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
