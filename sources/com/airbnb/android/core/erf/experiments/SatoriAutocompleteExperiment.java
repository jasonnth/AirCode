package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "use_satori_autocomplete")
public class SatoriAutocompleteExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
