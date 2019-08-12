package com.airbnb.android.lib.services;

final /* synthetic */ class OfficialIdIntentService$1$$Lambda$1 implements Runnable {
    private final C71731 arg$1;

    private OfficialIdIntentService$1$$Lambda$1(C71731 r1) {
        this.arg$1 = r1;
    }

    public static Runnable lambdaFactory$(C71731 r1) {
        return new OfficialIdIntentService$1$$Lambda$1(r1);
    }

    public void run() {
        C71731.lambda$onResponse$0(this.arg$1);
    }
}
