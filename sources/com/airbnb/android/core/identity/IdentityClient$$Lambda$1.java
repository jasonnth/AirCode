package com.airbnb.android.core.identity;

import com.airbnb.android.core.responses.GovernmentIdResultsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class IdentityClient$$Lambda$1 implements Action1 {
    private final IdentityClient arg$1;

    private IdentityClient$$Lambda$1(IdentityClient identityClient) {
        this.arg$1 = identityClient;
    }

    public static Action1 lambdaFactory$(IdentityClient identityClient) {
        return new IdentityClient$$Lambda$1(identityClient);
    }

    public void call(Object obj) {
        IdentityClient.lambda$new$0(this.arg$1, (GovernmentIdResultsResponse) obj);
    }
}
