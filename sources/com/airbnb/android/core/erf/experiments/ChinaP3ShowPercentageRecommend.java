package com.airbnb.android.core.erf.experiments;

import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_percentage_recommend")
public class ChinaP3ShowPercentageRecommend extends ExperimentConfig {
    public boolean isEnabled() {
        return true;
    }
}
