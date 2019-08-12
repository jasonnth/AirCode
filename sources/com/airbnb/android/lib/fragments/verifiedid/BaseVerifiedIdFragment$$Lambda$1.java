package com.airbnb.android.lib.fragments.verifiedid;

final /* synthetic */ class BaseVerifiedIdFragment$$Lambda$1 implements Runnable {
    private final BaseVerifiedIdFragment arg$1;
    private final String arg$2;
    private final String arg$3;

    private BaseVerifiedIdFragment$$Lambda$1(BaseVerifiedIdFragment baseVerifiedIdFragment, String str, String str2) {
        this.arg$1 = baseVerifiedIdFragment;
        this.arg$2 = str;
        this.arg$3 = str2;
    }

    public static Runnable lambdaFactory$(BaseVerifiedIdFragment baseVerifiedIdFragment, String str, String str2) {
        return new BaseVerifiedIdFragment$$Lambda$1(baseVerifiedIdFragment, str, str2);
    }

    public void run() {
        BaseVerifiedIdFragment.lambda$animateCompletion$0(this.arg$1, this.arg$2, this.arg$3);
    }
}
