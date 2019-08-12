package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.ExperimentConfig;

public class NoProfilePhotoV2Experiment extends ExperimentConfig {
    public boolean isEnabled() {
        return Trebuchet.launch(TrebuchetKeys.NO_PROFILE_PHOTO_V2);
    }
}
