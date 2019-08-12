package com.airbnb.android.lib.services;

final /* synthetic */ class OfficialIdIntentService$$Lambda$1 implements Runnable {
    private final OfficialIdIntentService arg$1;

    private OfficialIdIntentService$$Lambda$1(OfficialIdIntentService officialIdIntentService) {
        this.arg$1 = officialIdIntentService;
    }

    public static Runnable lambdaFactory$(OfficialIdIntentService officialIdIntentService) {
        return new OfficialIdIntentService$$Lambda$1(officialIdIntentService);
    }

    public void run() {
        OfficialIdIntentService.lambda$onHandleIntent$0(this.arg$1);
    }
}
