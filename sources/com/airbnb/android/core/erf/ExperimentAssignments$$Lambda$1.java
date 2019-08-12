package com.airbnb.android.core.erf;

import com.squareup.otto.Bus;

final /* synthetic */ class ExperimentAssignments$$Lambda$1 implements Runnable {
    private final ExperimentAssignments arg$1;
    private final Bus arg$2;

    private ExperimentAssignments$$Lambda$1(ExperimentAssignments experimentAssignments, Bus bus) {
        this.arg$1 = experimentAssignments;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(ExperimentAssignments experimentAssignments, Bus bus) {
        return new ExperimentAssignments$$Lambda$1(experimentAssignments, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}
