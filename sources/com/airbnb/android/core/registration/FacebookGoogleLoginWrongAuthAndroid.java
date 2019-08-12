package com.airbnb.android.core.registration;

import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_login_sheet")
public class FacebookGoogleLoginWrongAuthAndroid extends ExperimentConfig {
    public boolean shouldForceTreatment() {
        return BuildHelper.isDebugFeaturesEnabled();
    }

    public boolean isEnabled() {
        return true;
    }
}
