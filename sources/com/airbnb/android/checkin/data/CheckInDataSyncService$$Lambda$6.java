package com.airbnb.android.checkin.data;

import java.util.ArrayList;
import java.util.List;
import p032rx.functions.Action1;

final /* synthetic */ class CheckInDataSyncService$$Lambda$6 implements Action1 {
    private final CheckInDataSyncService arg$1;
    private final ArrayList arg$2;

    private CheckInDataSyncService$$Lambda$6(CheckInDataSyncService checkInDataSyncService, ArrayList arrayList) {
        this.arg$1 = checkInDataSyncService;
        this.arg$2 = arrayList;
    }

    public static Action1 lambdaFactory$(CheckInDataSyncService checkInDataSyncService, ArrayList arrayList) {
        return new CheckInDataSyncService$$Lambda$6(checkInDataSyncService, arrayList);
    }

    public void call(Object obj) {
        this.arg$1.diffRemoteAndLocalGuides(this.arg$2, (List) obj);
    }
}
