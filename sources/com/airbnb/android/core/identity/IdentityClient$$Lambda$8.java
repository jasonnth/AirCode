package com.airbnb.android.core.identity;

final /* synthetic */ class IdentityClient$$Lambda$8 implements Runnable {
    private final IdentityClient arg$1;

    private IdentityClient$$Lambda$8(IdentityClient identityClient) {
        this.arg$1 = identityClient;
    }

    public static Runnable lambdaFactory$(IdentityClient identityClient) {
        return new IdentityClient$$Lambda$8(identityClient);
    }

    public void run() {
        this.arg$1.checkGovernmentIdResult();
    }
}
