package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class AlipayNonBindingFlowExperiment extends ExperimentConfig {
    public static final String EXPERIMENT_NAME = "alipay_non_binding_flow_android";
    public static final String TREATMENT_NAME = "treatment";

    public boolean isEnabled() {
        return true;
    }

    public boolean shouldForceTreatment() {
        return BuildHelper.isDebugFeaturesEnabled();
    }
}
