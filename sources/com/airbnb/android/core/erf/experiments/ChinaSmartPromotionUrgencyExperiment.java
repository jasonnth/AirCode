package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class ChinaSmartPromotionUrgencyExperiment extends ExperimentConfig {
    public boolean isEnabled() {
        return ChinaUtils.isUserInChinaOrPrefersChineseLanguage();
    }
}
