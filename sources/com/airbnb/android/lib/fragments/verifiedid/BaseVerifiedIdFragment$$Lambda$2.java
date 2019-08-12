package com.airbnb.android.lib.fragments.verifiedid;

final /* synthetic */ class BaseVerifiedIdFragment$$Lambda$2 implements Runnable {
    private final BaseVerifiedIdFragment arg$1;

    private BaseVerifiedIdFragment$$Lambda$2(BaseVerifiedIdFragment baseVerifiedIdFragment) {
        this.arg$1 = baseVerifiedIdFragment;
    }

    public static Runnable lambdaFactory$(BaseVerifiedIdFragment baseVerifiedIdFragment) {
        return new BaseVerifiedIdFragment$$Lambda$2(baseVerifiedIdFragment);
    }

    public void run() {
        BaseVerifiedIdFragment.lambda$animateCompletion$1(this.arg$1);
    }
}
