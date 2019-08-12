package com.airbnb.android.checkin.data;

import java.util.concurrent.Callable;

final /* synthetic */ class CheckInDataSyncService$$Lambda$5 implements Callable {
    private final CheckInDataSyncService arg$1;

    private CheckInDataSyncService$$Lambda$5(CheckInDataSyncService checkInDataSyncService) {
        this.arg$1 = checkInDataSyncService;
    }

    public static Callable lambdaFactory$(CheckInDataSyncService checkInDataSyncService) {
        return new CheckInDataSyncService$$Lambda$5(checkInDataSyncService);
    }

    public Object call() {
        return this.arg$1.dbHelper.getAllGuides();
    }
}
