package com.airbnb.android.lib.fragments.verifiedid;

final /* synthetic */ class OnlineIdFragment$2$$Lambda$1 implements Runnable {
    private final C70322 arg$1;

    private OnlineIdFragment$2$$Lambda$1(C70322 r1) {
        this.arg$1 = r1;
    }

    public static Runnable lambdaFactory$(C70322 r1) {
        return new OnlineIdFragment$2$$Lambda$1(r1);
    }

    public void run() {
        C70322.lambda$onError$0(this.arg$1);
    }
}
