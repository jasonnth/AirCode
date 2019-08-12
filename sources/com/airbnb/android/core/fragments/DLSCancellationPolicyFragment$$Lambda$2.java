package com.airbnb.android.core.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class DLSCancellationPolicyFragment$$Lambda$2 implements Action1 {
    private final DLSCancellationPolicyFragment arg$1;

    private DLSCancellationPolicyFragment$$Lambda$2(DLSCancellationPolicyFragment dLSCancellationPolicyFragment) {
        this.arg$1 = dLSCancellationPolicyFragment;
    }

    public static Action1 lambdaFactory$(DLSCancellationPolicyFragment dLSCancellationPolicyFragment) {
        return new DLSCancellationPolicyFragment$$Lambda$2(dLSCancellationPolicyFragment);
    }

    public void call(Object obj) {
        DLSCancellationPolicyFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
