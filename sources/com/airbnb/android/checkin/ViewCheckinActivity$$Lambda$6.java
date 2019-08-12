package com.airbnb.android.checkin;

import java.util.concurrent.Callable;

final /* synthetic */ class ViewCheckinActivity$$Lambda$6 implements Callable {
    private final ViewCheckinActivity arg$1;

    private ViewCheckinActivity$$Lambda$6(ViewCheckinActivity viewCheckinActivity) {
        this.arg$1 = viewCheckinActivity;
    }

    public static Callable lambdaFactory$(ViewCheckinActivity viewCheckinActivity) {
        return new ViewCheckinActivity$$Lambda$6(viewCheckinActivity);
    }

    public Object call() {
        return this.arg$1.dbHelper.getCheckInGuideDataForId(this.arg$1.getListingId());
    }
}
