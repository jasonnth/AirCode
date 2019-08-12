package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.ExperimentConfig;

public class ShowUnfinishedListingOnProfileExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return Trebuchet.launch(TrebuchetKeys.SHOW_UNFINISHED_LISTING_ON_PROFILE);
    }
}
