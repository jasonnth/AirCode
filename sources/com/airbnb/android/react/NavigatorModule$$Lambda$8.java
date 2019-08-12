package com.airbnb.android.react;

final /* synthetic */ class NavigatorModule$$Lambda$8 implements Runnable {
    private final ReactInterface arg$1;

    private NavigatorModule$$Lambda$8(ReactInterface reactInterface) {
        this.arg$1 = reactInterface;
    }

    public static Runnable lambdaFactory$(ReactInterface reactInterface) {
        return new NavigatorModule$$Lambda$8(reactInterface);
    }

    public void run() {
        this.arg$1.signalFirstRenderComplete();
    }
}
