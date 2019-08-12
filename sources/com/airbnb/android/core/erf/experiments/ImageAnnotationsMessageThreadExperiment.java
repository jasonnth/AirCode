package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "should_allow_image_annotations")
public class ImageAnnotationsMessageThreadExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }

    public boolean shouldForceTreatment() {
        return BuildHelper.isFuture();
    }
}
