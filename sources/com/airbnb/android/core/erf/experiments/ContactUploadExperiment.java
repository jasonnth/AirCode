package com.airbnb.android.core.erf.experiments;

import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.ExperimentConfig;
import com.airbnb.erf.annotations.Treatment;

@Treatment(name = "show_contact_upload")
public class ContactUploadExperiment extends ExperimentConfig {
    public boolean shouldForceTreatment() {
        return BuildHelper.isFuture() && !CoreApplication.instance().isTestApplication();
    }

    public boolean isEnabled() {
        return Trebuchet.launch(TrebuchetKeys.ENABLE_ANDROID_ROLODEX_UPLOADING);
    }
}
