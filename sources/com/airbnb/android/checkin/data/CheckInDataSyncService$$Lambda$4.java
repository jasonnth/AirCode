package com.airbnb.android.checkin.data;

import p032rx.functions.Action1;

final /* synthetic */ class CheckInDataSyncService$$Lambda$4 implements Action1 {
    private final CheckInDataSyncService arg$1;

    private CheckInDataSyncService$$Lambda$4(CheckInDataSyncService checkInDataSyncService) {
        this.arg$1 = checkInDataSyncService;
    }

    public static Action1 lambdaFactory$(CheckInDataSyncService checkInDataSyncService) {
        return new CheckInDataSyncService$$Lambda$4(checkInDataSyncService);
    }

    public void call(Object obj) {
        this.arg$1.checkAndStopIfFinished();
    }
}
