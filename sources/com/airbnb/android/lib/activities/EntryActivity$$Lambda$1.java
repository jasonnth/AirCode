package com.airbnb.android.lib.activities;

final /* synthetic */ class EntryActivity$$Lambda$1 implements Runnable {
    private final EntryActivity arg$1;
    private final long arg$2;

    private EntryActivity$$Lambda$1(EntryActivity entryActivity, long j) {
        this.arg$1 = entryActivity;
        this.arg$2 = j;
    }

    public static Runnable lambdaFactory$(EntryActivity entryActivity, long j) {
        return new EntryActivity$$Lambda$1(entryActivity, j);
    }

    public void run() {
        this.arg$1.launchAnalytics.trackColdLaunchEnd(this.arg$2);
    }
}
