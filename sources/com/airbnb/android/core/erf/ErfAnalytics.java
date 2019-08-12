package com.airbnb.android.core.erf;

import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.DeviceInfo;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.utils.Strap;

public class ErfAnalytics {
    private static final String TAG = "ErfCallbacks";
    private final AirbnbAccountManager accountManager;
    private final String visitorId;

    public ErfAnalytics(DeviceInfo deviceInfo, AirbnbAccountManager accountManager2) {
        this.visitorId = deviceInfo.getAndroidId();
        this.accountManager = accountManager2;
    }

    public void logExperiment(ErfExperiment experiment, String treatmentName) {
        if (BuildHelper.isReleaseBuild()) {
            Strap strap = Strap.make().mo11639kv("experiment", experiment.experimentName()).mo11639kv("treatment", treatmentName).mo11638kv("experiment_version", experiment.version()).mo11638kv("experiment_timestamp", experiment.timestamp()).mo11639kv("subject_type", experiment.getSubject()).mo11639kv("visitor_id", this.visitorId);
            User currentUser = this.accountManager.getCurrentUser();
            if (currentUser != null) {
                strap.mo11638kv("user_id", currentUser.getId()).mo11639kv("subject_id", experiment.isSubjectUser() ? String.valueOf(currentUser.getId()) : this.visitorId);
            } else {
                strap.mo11639kv("subject_id", experiment.isSubjectUser() ? "" : this.visitorId);
            }
            AirbnbEventLogger.track("experiment_assignment", strap);
            return;
        }
        C0715L.m1189d(TAG, "Delivering treatment '" + treatmentName + "' for experiment '" + experiment + "'");
    }
}
