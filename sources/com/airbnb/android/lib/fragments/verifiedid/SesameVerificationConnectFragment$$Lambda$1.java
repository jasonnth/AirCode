package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.android.core.requests.SesameCreditVerificationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class SesameVerificationConnectFragment$$Lambda$1 implements Action1 {
    private final SesameVerificationConnectFragment arg$1;

    private SesameVerificationConnectFragment$$Lambda$1(SesameVerificationConnectFragment sesameVerificationConnectFragment) {
        this.arg$1 = sesameVerificationConnectFragment;
    }

    public static Action1 lambdaFactory$(SesameVerificationConnectFragment sesameVerificationConnectFragment) {
        return new SesameVerificationConnectFragment$$Lambda$1(sesameVerificationConnectFragment);
    }

    public void call(Object obj) {
        SesameVerificationConnectFragment.lambda$new$0(this.arg$1, (SesameCreditVerificationResponse) obj);
    }
}
