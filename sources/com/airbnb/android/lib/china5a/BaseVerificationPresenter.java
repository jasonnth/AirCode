package com.airbnb.android.lib.china5a;

import com.airbnb.android.core.analytics.FiveAxiomAnalytics;
import com.airbnb.android.lib.china5a.VerificationStepModel;
import p032rx.subscriptions.CompositeSubscription;

public abstract class BaseVerificationPresenter<M extends VerificationStepModel, V> {

    /* renamed from: cs */
    protected final CompositeSubscription f9513cs = new CompositeSubscription();
    protected final M model;
    protected final V view;

    /* access modifiers changed from: protected */
    public abstract String getStepName();

    public BaseVerificationPresenter(M model2, V view2) {
        this.model = model2;
        this.view = view2;
    }

    public final void finish() {
        FiveAxiomAnalytics.trackStepFinished(getStepName());
        this.model.finish();
    }

    public final void cancel() {
        FiveAxiomAnalytics.trackStepCancelled(getStepName());
        this.model.cancel();
    }

    /* access modifiers changed from: protected */
    public void start() {
        FiveAxiomAnalytics.trackStepStart(getStepName());
    }

    /* access modifiers changed from: protected */
    public void stop() {
        this.f9513cs.clear();
    }
}
