package com.airbnb.android.checkin.data;

import com.airbnb.android.core.models.CheckInGuide;
import p032rx.functions.Action0;

final /* synthetic */ class CheckInDataSyncService$$Lambda$10 implements Action0 {
    private final CheckInDataSyncService arg$1;
    private final CheckInGuide arg$2;

    private CheckInDataSyncService$$Lambda$10(CheckInDataSyncService checkInDataSyncService, CheckInGuide checkInGuide) {
        this.arg$1 = checkInDataSyncService;
        this.arg$2 = checkInGuide;
    }

    public static Action0 lambdaFactory$(CheckInDataSyncService checkInDataSyncService, CheckInGuide checkInGuide) {
        return new CheckInDataSyncService$$Lambda$10(checkInDataSyncService, checkInGuide);
    }

    public void call() {
        this.arg$1.dbHelper.insertCheckInGuide(this.arg$2);
    }
}
