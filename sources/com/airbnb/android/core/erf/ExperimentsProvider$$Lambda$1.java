package com.airbnb.android.core.erf;

import com.squareup.otto.Bus;

final /* synthetic */ class ExperimentsProvider$$Lambda$1 implements Runnable {
    private final ExperimentsProvider arg$1;
    private final Bus arg$2;

    private ExperimentsProvider$$Lambda$1(ExperimentsProvider experimentsProvider, Bus bus) {
        this.arg$1 = experimentsProvider;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(ExperimentsProvider experimentsProvider, Bus bus) {
        return new ExperimentsProvider$$Lambda$1(experimentsProvider, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}
