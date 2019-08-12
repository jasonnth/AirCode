package com.airbnb.android.checkin;

import com.airbnb.android.core.models.CheckInGuide;
import p032rx.functions.Action0;

final /* synthetic */ class ViewCheckinActivity$$Lambda$11 implements Action0 {
    private final ViewCheckinActivity arg$1;
    private final CheckInGuide arg$2;

    private ViewCheckinActivity$$Lambda$11(ViewCheckinActivity viewCheckinActivity, CheckInGuide checkInGuide) {
        this.arg$1 = viewCheckinActivity;
        this.arg$2 = checkInGuide;
    }

    public static Action0 lambdaFactory$(ViewCheckinActivity viewCheckinActivity, CheckInGuide checkInGuide) {
        return new ViewCheckinActivity$$Lambda$11(viewCheckinActivity, checkInGuide);
    }

    public void call() {
        this.arg$1.dbHelper.insertCheckInGuide(this.arg$2);
    }
}
