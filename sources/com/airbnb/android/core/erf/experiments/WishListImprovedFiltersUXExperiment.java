package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_improved_wish_list_filters_ux")
public class WishListImprovedFiltersUXExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
