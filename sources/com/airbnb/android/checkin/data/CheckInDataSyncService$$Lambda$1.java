package com.airbnb.android.checkin.data;

import com.airbnb.android.core.responses.CheckInGuideListResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CheckInDataSyncService$$Lambda$1 implements Action1 {
    private final CheckInDataSyncService arg$1;

    private CheckInDataSyncService$$Lambda$1(CheckInDataSyncService checkInDataSyncService) {
        this.arg$1 = checkInDataSyncService;
    }

    public static Action1 lambdaFactory$(CheckInDataSyncService checkInDataSyncService) {
        return new CheckInDataSyncService$$Lambda$1(checkInDataSyncService);
    }

    public void call(Object obj) {
        this.arg$1.handleUpdatedCheckInGuides(((CheckInGuideListResponse) obj).guides);
    }
}
