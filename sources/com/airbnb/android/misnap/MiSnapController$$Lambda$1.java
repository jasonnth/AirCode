package com.airbnb.android.misnap;

final /* synthetic */ class MiSnapController$$Lambda$1 implements Runnable {
    private final MiSnapController arg$1;

    private MiSnapController$$Lambda$1(MiSnapController miSnapController) {
        this.arg$1 = miSnapController;
    }

    public static Runnable lambdaFactory$(MiSnapController miSnapController) {
        return new MiSnapController$$Lambda$1(miSnapController);
    }

    public void run() {
        this.arg$1.switchToManualMode();
    }
}
