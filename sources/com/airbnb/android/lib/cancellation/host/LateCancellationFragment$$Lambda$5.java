package com.airbnb.android.lib.cancellation.host;

final /* synthetic */ class LateCancellationFragment$$Lambda$5 implements Runnable {
    private final LateCancellationFragment arg$1;

    private LateCancellationFragment$$Lambda$5(LateCancellationFragment lateCancellationFragment) {
        this.arg$1 = lateCancellationFragment;
    }

    public static Runnable lambdaFactory$(LateCancellationFragment lateCancellationFragment) {
        return new LateCancellationFragment$$Lambda$5(lateCancellationFragment);
    }

    public void run() {
        LateCancellationFragment.lambda$requestInternationalNumbers$5(this.arg$1);
    }
}
