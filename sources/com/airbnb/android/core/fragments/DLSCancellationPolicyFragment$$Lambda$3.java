package com.airbnb.android.core.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class DLSCancellationPolicyFragment$$Lambda$3 implements Action1 {
    private final DLSCancellationPolicyFragment arg$1;

    private DLSCancellationPolicyFragment$$Lambda$3(DLSCancellationPolicyFragment dLSCancellationPolicyFragment) {
        this.arg$1 = dLSCancellationPolicyFragment;
    }

    public static Action1 lambdaFactory$(DLSCancellationPolicyFragment dLSCancellationPolicyFragment) {
        return new DLSCancellationPolicyFragment$$Lambda$3(dLSCancellationPolicyFragment);
    }

    public void call(Object obj) {
        this.arg$1.requestPerformanceLogger.markRestLoadEnded();
    }
}
