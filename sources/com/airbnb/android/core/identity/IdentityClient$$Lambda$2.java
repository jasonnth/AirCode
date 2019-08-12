package com.airbnb.android.core.identity;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class IdentityClient$$Lambda$2 implements Action1 {
    private final IdentityClient arg$1;

    private IdentityClient$$Lambda$2(IdentityClient identityClient) {
        this.arg$1 = identityClient;
    }

    public static Action1 lambdaFactory$(IdentityClient identityClient) {
        return new IdentityClient$$Lambda$2(identityClient);
    }

    public void call(Object obj) {
        this.arg$1.listener.identityCheckError((AirRequestNetworkException) obj);
    }
}
