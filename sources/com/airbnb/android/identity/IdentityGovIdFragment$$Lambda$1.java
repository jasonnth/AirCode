package com.airbnb.android.identity;

final /* synthetic */ class IdentityGovIdFragment$$Lambda$1 implements Runnable {
    private final IdentityGovIdFragment arg$1;

    private IdentityGovIdFragment$$Lambda$1(IdentityGovIdFragment identityGovIdFragment) {
        this.arg$1 = identityGovIdFragment;
    }

    public static Runnable lambdaFactory$(IdentityGovIdFragment identityGovIdFragment) {
        return new IdentityGovIdFragment$$Lambda$1(identityGovIdFragment);
    }

    public void run() {
        this.arg$1.startIdScan();
    }
}
