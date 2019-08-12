package com.airbnb.android.checkin.data;

import com.airbnb.android.core.models.CheckInGuide;
import com.google.common.base.Predicate;

final /* synthetic */ class CheckInDataSyncService$$Lambda$12 implements Predicate {
    private final long arg$1;

    private CheckInDataSyncService$$Lambda$12(long j) {
        this.arg$1 = j;
    }

    public static Predicate lambdaFactory$(long j) {
        return new CheckInDataSyncService$$Lambda$12(j);
    }

    public boolean apply(Object obj) {
        return CheckInDataSyncService.lambda$isMissingRemoteGuide$10(this.arg$1, (CheckInGuide) obj);
    }
}
