package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "should_show_children_infant")
public class ShowExpandedGuestPicker extends ExperimentConfig {
    public boolean isPhoneOnly() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }
}
