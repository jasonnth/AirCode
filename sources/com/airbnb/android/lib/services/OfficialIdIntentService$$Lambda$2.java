package com.airbnb.android.lib.services;

final /* synthetic */ class OfficialIdIntentService$$Lambda$2 implements Runnable {
    private final OfficialIdIntentService arg$1;

    private OfficialIdIntentService$$Lambda$2(OfficialIdIntentService officialIdIntentService) {
        this.arg$1 = officialIdIntentService;
    }

    public static Runnable lambdaFactory$(OfficialIdIntentService officialIdIntentService) {
        return new OfficialIdIntentService$$Lambda$2(officialIdIntentService);
    }

    public void run() {
        OfficialIdIntentService.lambda$onDestroy$1(this.arg$1);
    }
}
