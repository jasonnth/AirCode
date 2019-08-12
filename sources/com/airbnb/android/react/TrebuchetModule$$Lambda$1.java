package com.airbnb.android.react;

import com.squareup.otto.Bus;

final /* synthetic */ class TrebuchetModule$$Lambda$1 implements Runnable {
    private final TrebuchetModule arg$1;
    private final Bus arg$2;

    private TrebuchetModule$$Lambda$1(TrebuchetModule trebuchetModule, Bus bus) {
        this.arg$1 = trebuchetModule;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(TrebuchetModule trebuchetModule, Bus bus) {
        return new TrebuchetModule$$Lambda$1(trebuchetModule, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}
