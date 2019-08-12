package com.airbnb.android.react;

import com.squareup.otto.Bus;

final /* synthetic */ class ExperimentModule$$Lambda$1 implements Runnable {
    private final ExperimentModule arg$1;
    private final Bus arg$2;

    private ExperimentModule$$Lambda$1(ExperimentModule experimentModule, Bus bus) {
        this.arg$1 = experimentModule;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(ExperimentModule experimentModule, Bus bus) {
        return new ExperimentModule$$Lambda$1(experimentModule, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}
