package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class SesameVerificationConnectFragment$$Lambda$2 implements Action1 {
    private final SesameVerificationConnectFragment arg$1;

    private SesameVerificationConnectFragment$$Lambda$2(SesameVerificationConnectFragment sesameVerificationConnectFragment) {
        this.arg$1 = sesameVerificationConnectFragment;
    }

    public static Action1 lambdaFactory$(SesameVerificationConnectFragment sesameVerificationConnectFragment) {
        return new SesameVerificationConnectFragment$$Lambda$2(sesameVerificationConnectFragment);
    }

    public void call(Object obj) {
        SesameVerificationConnectFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
