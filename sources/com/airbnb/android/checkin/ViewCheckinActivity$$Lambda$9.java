package com.airbnb.android.checkin;

import p032rx.functions.Action1;

final /* synthetic */ class ViewCheckinActivity$$Lambda$9 implements Action1 {
    private final ViewCheckinActivity arg$1;

    private ViewCheckinActivity$$Lambda$9(ViewCheckinActivity viewCheckinActivity) {
        this.arg$1 = viewCheckinActivity;
    }

    public static Action1 lambdaFactory$(ViewCheckinActivity viewCheckinActivity) {
        return new ViewCheckinActivity$$Lambda$9(viewCheckinActivity);
    }

    public void call(Object obj) {
        this.arg$1.handleDatabaseGuideNotFound((Throwable) obj);
    }
}
