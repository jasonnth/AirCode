package com.airbnb.android.checkin.data;

import com.airbnb.android.core.models.CheckInGuide;
import com.google.common.base.Predicate;
import java.util.List;

final /* synthetic */ class CheckInDataSyncService$$Lambda$9 implements Predicate {
    private final List arg$1;

    private CheckInDataSyncService$$Lambda$9(List list) {
        this.arg$1 = list;
    }

    public static Predicate lambdaFactory$(List list) {
        return new CheckInDataSyncService$$Lambda$9(list);
    }

    public boolean apply(Object obj) {
        return CheckInDataSyncService.isMissingOrOutdated(this.arg$1, (CheckInGuide) obj);
    }
}
