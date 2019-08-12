package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "treatment")
public class ChinaSmartPromotionV1Experiment extends ExperimentConfig {
    public boolean isEnabled() {
        return ChinaUtils.isUserInChinaOrPrefersChineseLanguage();
    }
}
