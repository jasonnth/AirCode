package com.airbnb.android.core.views;

final /* synthetic */ class MovablePinMap$$Lambda$4 implements Runnable {
    private final MovablePinMap arg$1;

    private MovablePinMap$$Lambda$4(MovablePinMap movablePinMap) {
        this.arg$1 = movablePinMap;
    }

    public static Runnable lambdaFactory$(MovablePinMap movablePinMap) {
        return new MovablePinMap$$Lambda$4(movablePinMap);
    }

    public void run() {
        MovablePinMap.lambda$initialize$0(this.arg$1);
    }
}
