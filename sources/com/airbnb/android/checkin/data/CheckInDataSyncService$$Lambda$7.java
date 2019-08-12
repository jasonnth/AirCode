package com.airbnb.android.checkin.data;

import com.google.common.base.Predicate;
import java.util.ArrayList;

final /* synthetic */ class CheckInDataSyncService$$Lambda$7 implements Predicate {
    private final ArrayList arg$1;

    private CheckInDataSyncService$$Lambda$7(ArrayList arrayList) {
        this.arg$1 = arrayList;
    }

    public static Predicate lambdaFactory$(ArrayList arrayList) {
        return new CheckInDataSyncService$$Lambda$7(arrayList);
    }

    public boolean apply(Object obj) {
        return CheckInDataSyncService.isMissingRemoteGuide(this.arg$1, ((CheckInGuideData) obj).listing_id());
    }
}
