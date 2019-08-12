package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "use_dynamic_image_size_on_p3")
public class P3DynamicImageSizeExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return false;
    }
}
