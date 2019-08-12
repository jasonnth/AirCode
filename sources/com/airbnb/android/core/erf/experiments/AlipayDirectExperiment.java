package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "alipay_direct_enabled")
public class AlipayDirectExperiment extends ExperimentConfig {
    public boolean shouldForceTreatment() {
        return BuildHelper.isFuture();
    }

    public boolean isEnabled() {
        return Trebuchet.launch(TrebuchetKeys.ALIPAY_DIRECT, false);
    }
}
