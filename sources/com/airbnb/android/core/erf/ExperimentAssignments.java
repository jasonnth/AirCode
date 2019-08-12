package com.airbnb.android.core.erf;

import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.util.ArrayMap;
import com.airbnb.android.core.events.ErfExperimentsUpdatedEvent;
import com.airbnb.android.core.events.LogoutEvent;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.erf.Erf;
import com.airbnb.erf.ExperimentConfig;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.Map;

public class ExperimentAssignments {
    private final Map<String, String> assignmentMap = new ArrayMap();
    private final Erf erf;
    private final Map<String, String> holdoutContainerMap = new ArrayMap();
    private final boolean isTablet;
    private final Map<String, String> stickyAssignmentMap = new ArrayMap();

    public ExperimentAssignments(Bus bus, Erf erf2, boolean isTablet2) {
        this.erf = erf2;
        this.isTablet = isTablet2;
        new Handler(Looper.getMainLooper()).post(ExperimentAssignments$$Lambda$1.lambdaFactory$(this, bus));
        this.holdoutContainerMap.putAll(erf2.getHoldoutExperimentMap());
    }

    @Subscribe
    public void onExperimentsChanged(ErfExperimentsUpdatedEvent event) {
        clearNonStickyAssignments();
    }

    @Subscribe
    public void onUserLogOut(LogoutEvent logoutEvent) {
        clearAllAssignments();
    }

    public void clearAllAssignments() {
        this.assignmentMap.clear();
        this.stickyAssignmentMap.clear();
    }

    /* access modifiers changed from: 0000 */
    public void clearNonStickyAssignments() {
        this.assignmentMap.clear();
    }

    public boolean isTablet() {
        return this.isTablet;
    }

    public String getCachedAssignment(String experimentName) {
        if (this.stickyAssignmentMap.containsKey(experimentName)) {
            return (String) this.stickyAssignmentMap.get(experimentName);
        }
        return (String) this.assignmentMap.get(experimentName);
    }

    public String getAssignment(String experimentName, ExperimentConfig config) {
        String cachedAssignment = getCachedAssignment(experimentName);
        if (cachedAssignment != null) {
            return cachedAssignment;
        }
        if (this.holdoutContainerMap.containsKey(experimentName)) {
            this.erf.deliverExperiment((String) this.holdoutContainerMap.get(experimentName));
        }
        return assignExperimentAndDeliverIfEnabled(experimentName, config);
    }

    private String assignExperimentAndDeliverIfEnabled(String experimentName, ExperimentConfig config) {
        String assignedTreatment;
        if (config.isPhoneOnly() && isTablet()) {
            assignedTreatment = AirbnbConstants.NOT_IN_EXPERIMENT_KEY;
        } else if (config.shouldForceTreatment()) {
            assignedTreatment = config.treatmentToForce();
        } else if (!config.isEnabled()) {
            assignedTreatment = AirbnbConstants.NOT_IN_EXPERIMENT_KEY;
        } else {
            assignedTreatment = this.erf.deliverExperiment(experimentName);
        }
        if (config.shouldUseStickyAssignments()) {
            this.stickyAssignmentMap.put(experimentName, assignedTreatment);
        } else {
            this.assignmentMap.put(experimentName, assignedTreatment);
        }
        return assignedTreatment;
    }
}
