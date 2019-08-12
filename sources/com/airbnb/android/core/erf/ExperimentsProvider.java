package com.airbnb.android.core.erf;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.util.ArrayMap;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.events.ErfExperimentsRefreshEvent;
import com.airbnb.android.core.events.ErfExperimentsUpdatedEvent;
import com.airbnb.android.core.events.LogoutEvent;
import com.airbnb.android.core.requests.ErfExperimentsRequest;
import com.airbnb.android.core.responses.ErfExperimentsResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.Strap;
import com.airbnb.erf.Experiment;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import p032rx.Observer;

public class ExperimentsProvider implements com.airbnb.erf.ExperimentsProvider {
    private static final String ERF_OVERRIDE_PREFS = "erf_override_prefs";
    private static final String ERF_PREFS = "erf_prefs";
    /* access modifiers changed from: private */
    public final Bus bus;
    private final Context context;
    private Map<String, ErfExperiment> experimentsMap;
    private final ErfExperimentsTableOpenHelper tableHelper;

    class ErfExperimentsListener extends NonResubscribableRequestListener<ErfExperimentsResponse> {
        ErfExperimentsListener() {
        }

        public void onResponse(ErfExperimentsResponse response) {
            ExperimentsProvider.this.bus.post(new ErfExperimentsUpdatedEvent());
            ExperimentsProvider.this.bus.post(new ErfExperimentsRefreshEvent(true));
        }

        public void onErrorResponse(AirRequestNetworkException error) {
            ExperimentsProvider.this.bus.post(new ErfExperimentsRefreshEvent(false));
        }
    }

    public ExperimentsProvider(Context context2, Bus bus2, ErfExperimentsTableOpenHelper tableHelper2) {
        this.context = context2;
        this.bus = bus2;
        this.tableHelper = tableHelper2;
        new Handler(Looper.getMainLooper()).post(ExperimentsProvider$$Lambda$1.lambdaFactory$(this, bus2));
    }

    public BaseRequestV2<ErfExperimentsResponse> newErfExperimentRequest(boolean isBatched) {
        return new ErfExperimentsRequest(isBatched).withListener((Observer) new ErfExperimentsListener());
    }

    public String getExperimentDebugOutput() {
        return FluentIterable.from((Iterable<E>) getExperiments().values()).transform(ExperimentsProvider$$Lambda$2.lambdaFactory$()).join(Joiner.m1896on("\n"));
    }

    static /* synthetic */ String lambda$getExperimentDebugOutput$1(ErfExperiment e) {
        return e.experimentName() + ": " + e.assignedTreatment();
    }

    public Map<String, ErfExperiment> getExperiments() {
        synchronized (this) {
            if (this.experimentsMap == null) {
                this.experimentsMap = fetchExperimentsFromDatabase();
                restoreOverriddenAssignmentsForTesting();
            }
        }
        return this.experimentsMap;
    }

    public void resetExperiments(ErfExperimentsRequest request, ErfExperimentsResponse response) {
        if (!response.metadata.isCached() && !request.isBatched()) {
            trackErfFetchResult(request.startTime(), true);
        }
        List<ErfExperiment> experiments = FluentIterable.from((Iterable<E>) response.experiments).transform(ExperimentsProvider$$Lambda$3.lambdaFactory$(response)).toList();
        this.experimentsMap = new ArrayMap(experiments.size());
        synchronized (this) {
            for (ErfExperiment experiment : experiments) {
                addExperiment(experiment);
            }
        }
        restoreOverriddenAssignmentsForTesting();
        persistExperiments();
    }

    public ErfExperiment getExperiment(String experimentName) {
        return (ErfExperiment) getExperiments().get(experimentName.toLowerCase());
    }

    public List<Experiment> getExperimentsWithHoldout() {
        Map<String, ErfExperiment> experiments = getExperiments();
        return FluentIterable.from((Iterable<E>) experiments.values()).transform(ExperimentsProvider$$Lambda$4.lambdaFactory$()).filter(ExperimentsProvider$$Lambda$5.lambdaFactory$()).filter(ExperimentsProvider$$Lambda$6.lambdaFactory$(experiments)).toList();
    }

    static /* synthetic */ Experiment lambda$getExperimentsWithHoldout$3(ErfExperiment e) {
        return e;
    }

    public void addExperiment(ErfExperiment experiment) {
        getExperiments().put(experiment.getName().toLowerCase(), experiment);
    }

    public void addExperimentWithOverriddenAssignmentForTesting(ErfExperiment experiment) {
        addExperiment(experiment);
        this.context.getSharedPreferences(ERF_OVERRIDE_PREFS, 0).edit().putString(experiment.getName(), experiment.getAssignedTreatment()).apply();
        this.bus.post(new ErfExperimentsUpdatedEvent(experiment.experimentName()));
    }

    public Map<String, String> getOverriddenExperiments() {
        SharedPreferences prefs = this.context.getSharedPreferences(ERF_OVERRIDE_PREFS, 0);
        Set<String> overriddenExperimentNames = prefs.getAll().keySet();
        Map<String, String> experiments = new ArrayMap<>();
        for (String experimentName : overriddenExperimentNames) {
            experiments.put(experimentName, prefs.getString(experimentName, null));
        }
        return experiments;
    }

    private void restoreOverriddenAssignmentsForTesting() {
        if (BuildHelper.isFuture() || BuildHelper.isDebugFeaturesEnabled()) {
            SharedPreferences prefs = this.context.getSharedPreferences(ERF_OVERRIDE_PREFS, 0);
            Set<String> overriddenExperimentNames = prefs.getAll().keySet();
            List<ErfExperiment> overriddenExperiments = new ArrayList<>(overriddenExperimentNames.size());
            for (String experimentName : overriddenExperimentNames) {
                String assignedTreatment = prefs.getString(experimentName, null);
                overriddenExperiments.add(new ErfExperiment(experimentName, assignedTreatment, Collections.singletonList(assignedTreatment), "user", 0, 0));
            }
            for (ErfExperiment overriddenExperiment : overriddenExperiments) {
                ErfExperiment serverExperiment = (ErfExperiment) this.experimentsMap.get(overriddenExperiment.getName());
                if (serverExperiment == null) {
                    addExperiment(overriddenExperiment);
                } else {
                    serverExperiment.setAssignedTreatment(overriddenExperiment.getAssignedTreatment());
                }
            }
        }
    }

    public Map<String, ErfExperiment> fetchExperimentsFromDatabase() {
        List<ErfExperiment> allExperiments = this.tableHelper.getAllExperiments();
        Map<String, ErfExperiment> experiments = new ArrayMap<>();
        for (ErfExperiment experiment : allExperiments) {
            experiments.put(experiment.experimentName(), experiment);
        }
        return experiments;
    }

    public synchronized void persistExperiments() {
        this.context.getSharedPreferences(ERF_PREFS, 0).edit().clear().apply();
        this.tableHelper.clear();
        this.tableHelper.insert(this.experimentsMap.values());
    }

    public void refreshExperimentsFromServer(boolean expireFirst) {
        newErfExperimentRequest(false).skipCache(expireFirst).execute(NetworkUtil.singleFireExecutor());
    }

    private void trackErfFetchResult(long startTime, boolean success) {
        AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv(BaseAnalytics.OPERATION, "erf_fetch_complete").mo11640kv("success", success).mo11638kv("duration_ms", (System.nanoTime() - startTime) / 1000000));
    }

    @Subscribe
    public void onUserLogout(LogoutEvent event) {
        clearUserExperiments();
        refreshExperimentsFromServer(true);
    }

    private void clearUserExperiments() {
        Iterator<Entry<String, ErfExperiment>> iterator = getExperiments().entrySet().iterator();
        boolean experimentsUpdated = false;
        while (iterator.hasNext()) {
            if (((Experiment) ((Entry) iterator.next()).getValue()).isSubjectUser()) {
                iterator.remove();
                experimentsUpdated = true;
            }
        }
        if (experimentsUpdated) {
            this.bus.post(new ErfExperimentsUpdatedEvent());
        }
        persistExperiments();
    }
}
