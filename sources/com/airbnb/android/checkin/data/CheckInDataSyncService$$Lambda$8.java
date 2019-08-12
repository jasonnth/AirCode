package com.airbnb.android.checkin.data;

import p032rx.functions.Action0;

final /* synthetic */ class CheckInDataSyncService$$Lambda$8 implements Action0 {
    private final CheckInDataSyncService arg$1;
    private final CheckInGuideData arg$2;

    private CheckInDataSyncService$$Lambda$8(CheckInDataSyncService checkInDataSyncService, CheckInGuideData checkInGuideData) {
        this.arg$1 = checkInDataSyncService;
        this.arg$2 = checkInGuideData;
    }

    public static Action0 lambdaFactory$(CheckInDataSyncService checkInDataSyncService, CheckInGuideData checkInGuideData) {
        return new CheckInDataSyncService$$Lambda$8(checkInDataSyncService, checkInGuideData);
    }

    public void call() {
        this.arg$1.dbHelper.deleteGuide(this.arg$2.listing_id());
    }
}
