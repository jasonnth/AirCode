package com.airbnb.android.core.controllers;

import p032rx.functions.Action1;

final /* synthetic */ class ExperimentConfigController$$Lambda$2 implements Action1 {
    private final ExperimentConfigController arg$1;

    private ExperimentConfigController$$Lambda$2(ExperimentConfigController experimentConfigController) {
        this.arg$1 = experimentConfigController;
    }

    public static Action1 lambdaFactory$(ExperimentConfigController experimentConfigController) {
        return new ExperimentConfigController$$Lambda$2(experimentConfigController);
    }

    public void call(Object obj) {
        this.arg$1.onRequestFinish(false);
    }
}
