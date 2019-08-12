package com.airbnb.android.react;

import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.util.ArrayMap;
import com.airbnb.android.core.erf.ErfExperiment;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.events.ErfExperimentsRefreshEvent;
import com.airbnb.android.core.events.ErfExperimentsUpdatedEvent;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.facebook.react.bridge.ReactApplicationContext;
import com.google.common.collect.ImmutableMap;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.Map;
import java.util.Map.Entry;

class ExperimentModule extends VersionedReactModuleBase {
    private static final String EXPERIMENTS_UPDATED_EVENT_NAME = "airbnb.experimentsUpdated";
    private static final int VERSION = 1;
    private final ExperimentsProvider experimentsProvider;

    ExperimentModule(ReactApplicationContext reactContext, ExperimentsProvider experimentsProvider2, Bus bus) {
        super(reactContext, 1);
        this.experimentsProvider = experimentsProvider2;
        new Handler(Looper.getMainLooper()).post(ExperimentModule$$Lambda$1.lambdaFactory$(this, bus));
    }

    public String getName() {
        return "ExperimentBridge";
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = super.getConstants();
        Map<String, Object> data = getExperimentsAndOverrides();
        constants.put("initialExperiments", data.get("experiments"));
        constants.put("initialExperimentOverrides", data.get("experimentOverrides"));
        return constants;
    }

    private Map<String, Object> getExperimentsAndOverrides() {
        Map<String, ErfExperiment> experimentObjects = this.experimentsProvider.fetchExperimentsFromDatabase();
        Map<String, Object> experiments = new ArrayMap<>();
        for (Entry<String, ErfExperiment> entry : experimentObjects.entrySet()) {
            experiments.put(entry.getKey(), ImmutableMap.m1295of(ErfExperimentsModel.TREATMENTS, ((ErfExperiment) entry.getValue()).getTreatments(), "assigned_treatment", ((ErfExperiment) entry.getValue()).getAssignedTreatment(), ErfExperimentsModel.SUBJECT, ((ErfExperiment) entry.getValue()).getSubject()));
        }
        Map<String, String> experimentOverrideObjects = this.experimentsProvider.getOverriddenExperiments();
        Map<String, Object> experimentOverrides = new ArrayMap<>();
        for (Entry<String, String> entry2 : experimentOverrideObjects.entrySet()) {
            experimentOverrides.put(entry2.getKey(), entry2.getValue());
        }
        return ImmutableMap.m1294of("experiments", experiments, "experimentOverrides", experimentOverrides);
    }

    @Subscribe
    public void onExperimentsRefreshed(ErfExperimentsRefreshEvent e) {
        if (e.isRefreshSuccessful()) {
            ReactNativeUtils.maybeEmitEvent(getReactApplicationContext(), EXPERIMENTS_UPDATED_EVENT_NAME, ConversionUtil.toWritableMap(getExperimentsAndOverrides()));
        }
    }

    @Subscribe
    public void onExperimentsUpdated(ErfExperimentsUpdatedEvent e) {
        ReactNativeUtils.maybeEmitEvent(getReactApplicationContext(), EXPERIMENTS_UPDATED_EVENT_NAME, ConversionUtil.toWritableMap(getExperimentsAndOverrides()));
    }
}
