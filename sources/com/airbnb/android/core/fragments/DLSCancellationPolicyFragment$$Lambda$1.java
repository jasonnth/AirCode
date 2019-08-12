package com.airbnb.android.core.fragments;

import com.airbnb.android.core.responses.GetCancellationPolicyResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DLSCancellationPolicyFragment$$Lambda$1 implements Action1 {
    private final DLSCancellationPolicyFragment arg$1;

    private DLSCancellationPolicyFragment$$Lambda$1(DLSCancellationPolicyFragment dLSCancellationPolicyFragment) {
        this.arg$1 = dLSCancellationPolicyFragment;
    }

    public static Action1 lambdaFactory$(DLSCancellationPolicyFragment dLSCancellationPolicyFragment) {
        return new DLSCancellationPolicyFragment$$Lambda$1(dLSCancellationPolicyFragment);
    }

    public void call(Object obj) {
        DLSCancellationPolicyFragment.lambda$new$0(this.arg$1, (GetCancellationPolicyResponse) obj);
    }
}
