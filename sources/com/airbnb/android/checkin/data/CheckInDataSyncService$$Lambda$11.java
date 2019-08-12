package com.airbnb.android.checkin.data;

import com.airbnb.android.core.models.CheckInGuide;
import com.google.common.base.Predicate;

final /* synthetic */ class CheckInDataSyncService$$Lambda$11 implements Predicate {
    private final CheckInGuide arg$1;

    private CheckInDataSyncService$$Lambda$11(CheckInGuide checkInGuide) {
        this.arg$1 = checkInGuide;
    }

    public static Predicate lambdaFactory$(CheckInGuide checkInGuide) {
        return new CheckInDataSyncService$$Lambda$11(checkInGuide);
    }

    public boolean apply(Object obj) {
        return CheckInDataSyncService.lambda$isMissingOrOutdated$9(this.arg$1, (CheckInGuideData) obj);
    }
}
