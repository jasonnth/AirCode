package com.airbnb.android.core.controllers;

import android.content.Context;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.events.ExperimentConfigFetchCompleteEvent;
import com.airbnb.android.core.events.TrebuchetUpdatedEvent;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.GetCampaignsRequest;
import com.airbnb.android.core.requests.MarioExperimentRequest;
import com.airbnb.android.core.requests.TrebuchetRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.TrebuchetResponse;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import com.google.common.collect.ImmutableList;
import com.squareup.otto.Bus;
import p032rx.Observer;

public class ExperimentConfigController {
    private static final String APP_START_EVENT_FETCHING = "app_start_event_fetching";
    private final Bus bus;
    private final Context context;
    private final NonResubscribableRequestListener<AirBatchResponse> experimentConfigRequestListener = new NonResubscribableRequestListener<AirBatchResponse>() {
        public void onResponse(AirBatchResponse data) {
            ExperimentConfigController.this.onRequestFinish(true);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            ExperimentConfigController.this.onRequestFinish(false);
        }
    };
    private final ExperimentsProvider experimentsProvider;
    private final PerformanceLogger performanceLogger;
    private boolean requestInFlight;
    private final RequestManager requestManager;
    private long requestUserId;
    private final TrebuchetController trebuchetController;
    final RequestListener<TrebuchetResponse> trebuchetRequestListener = new C0699RL().onResponse(ExperimentConfigController$$Lambda$1.lambdaFactory$(this)).onError(ExperimentConfigController$$Lambda$2.lambdaFactory$(this)).build();

    /* access modifiers changed from: private */
    public void onRequestFinish(boolean success) {
        this.performanceLogger.markCompleted(APP_START_EVENT_FETCHING, C2445NativeMeasurementType.ActionDuration, null);
        this.requestInFlight = false;
        this.bus.post(new ExperimentConfigFetchCompleteEvent(success, this.requestUserId));
        this.bus.post(new TrebuchetUpdatedEvent());
    }

    public ExperimentConfigController(Context context2, AirRequestInitializer initializer, ExperimentsProvider experimentsProvider2, Bus bus2, PerformanceLogger performanceLogger2, TrebuchetController trebuchetController2) {
        this.context = context2;
        this.experimentsProvider = experimentsProvider2;
        this.bus = bus2;
        this.performanceLogger = performanceLogger2;
        this.trebuchetController = trebuchetController2;
        this.requestManager = RequestManager.onCreate(initializer, this, null);
        this.requestManager.onResume();
    }

    public void fetchConfigurationForUser(long userId) {
        if (!this.requestInFlight || userId != this.requestUserId) {
            this.requestUserId = userId;
            this.requestInFlight = true;
            TrebuchetRequest.create(this.trebuchetController).withListener((Observer) this.trebuchetRequestListener).execute(this.requestManager);
        }
    }

    /* access modifiers changed from: private */
    public void fetchExperimentAndV1Trebuchet() {
        this.performanceLogger.markStart(APP_START_EVENT_FETCHING);
        new AirBatchRequest(ImmutableList.m1287of(this.experimentsProvider.newErfExperimentRequest(true), GetCampaignsRequest.forLocation(LocationUtil.getLastKnownLocation(this.context)), MarioExperimentRequest.newRequest()), this.experimentConfigRequestListener).execute(this.requestManager);
    }
}
