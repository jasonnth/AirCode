package com.airbnb.android.core.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.erf.ErfExperiment;
import com.airbnb.android.core.erf.experiments.ColdStartOptimizationExperiment;
import com.airbnb.android.core.events.ErfExperimentsUpdatedEvent;
import com.airbnb.erf.Experiments;
import com.squareup.otto.Subscribe;
import p032rx.Observable;
import p032rx.schedulers.Schedulers;

public class ColdStartExperimentDeliverer {
    private static final int CONTROL = 1;
    private static final String KEY_ASSIGNMENT = "cold_start_assignment";
    private static final String SHARED_PREFS_NAME = "airbnb_cold_start_prefs";
    private static final int TREATMENT = 2;
    private static final int UNDEFINED = -1;
    private static final int UNKNOWN = 0;
    private static int assignment = -1;
    private static boolean experimentDelivered;
    private static boolean initialized;
    private static SharedPreferences sharedPrefs;

    private ColdStartExperimentDeliverer() {
    }

    public static void init() {
        if (!initialized) {
            initialized = true;
            CoreApplication.instance().component().bus().register(new ColdStartExperimentDeliverer());
            sharedPrefs = CoreApplication.appContext().getSharedPreferences(SHARED_PREFS_NAME, 0);
        }
    }

    public static boolean isInTreatmentGroup() {
        return getAssignment() != 1 && (BuildHelper.isDebugFeaturesEnabled() || getAssignment() == 2);
    }

    public static void deliverExperimentIfNeeded() {
        if (!experimentDelivered && assignment != -1 && assignment != 0) {
            experimentDelivered = true;
            Experiments.enableColdStartOptimization();
        }
    }

    private static int getAssignment() {
        if (assignment == -1) {
            assignment = sharedPrefs.getInt(KEY_ASSIGNMENT, 0);
        }
        return assignment;
    }

    @Subscribe
    public void onExperimentsUpdated(ErfExperimentsUpdatedEvent event) {
        loadAssignment();
    }

    private static void loadAssignment() {
        Observable.fromCallable(ColdStartExperimentDeliverer$$Lambda$1.lambdaFactory$()).subscribeOn(Schedulers.m4048io()).subscribe();
    }

    static /* synthetic */ Boolean lambda$loadAssignment$0() throws Exception {
        int i;
        ErfExperiment experiment = CoreApplication.instance().component().experimentsProvider().getExperiment(ColdStartOptimizationExperiment.EXPERIMENT_NAME);
        if (experiment != null) {
            boolean isTreatment = TextUtils.equals(experiment.getAssignedTreatment(), "treatment");
            Editor edit = sharedPrefs.edit();
            String str = KEY_ASSIGNMENT;
            if (isTreatment) {
                i = 2;
            } else {
                i = 1;
            }
            edit.putInt(str, i).apply();
        }
        return Boolean.valueOf(true);
    }
}
