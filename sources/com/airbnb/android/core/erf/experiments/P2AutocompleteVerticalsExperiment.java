package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_autocomplete_verticals")
public class P2AutocompleteVerticalsExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
