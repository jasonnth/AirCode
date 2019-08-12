package com.airbnb.erf;

import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.erf.ExperimentAssignments;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.utils.AirbnbConstants;

class _Experiments {
    private static ExperimentAssignments assignments() {
        return CoreApplication.instance().component().experimentAssigments();
    }

    protected static String getAssignment(String experimentName) {
        if (BuildHelper.optEnabled("force_erf_defaults")) {
            return AirbnbConstants.NOT_IN_EXPERIMENT_KEY;
        }
        return assignments().getCachedAssignment(experimentName);
    }

    protected static String assign(String experimentName, ExperimentConfig experiment) {
        return assignments().getAssignment(experimentName, experiment);
    }
}
