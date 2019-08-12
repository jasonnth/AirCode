package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_message_on_invite_page")
public class CohostInviteShowMessageFieldExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
