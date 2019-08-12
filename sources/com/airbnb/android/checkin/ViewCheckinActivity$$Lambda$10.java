package com.airbnb.android.checkin;

import p032rx.functions.Action0;

final /* synthetic */ class ViewCheckinActivity$$Lambda$10 implements Action0 {
    private final ViewCheckinActivity arg$1;

    private ViewCheckinActivity$$Lambda$10(ViewCheckinActivity viewCheckinActivity) {
        this.arg$1 = viewCheckinActivity;
    }

    public static Action0 lambdaFactory$(ViewCheckinActivity viewCheckinActivity) {
        return new ViewCheckinActivity$$Lambda$10(viewCheckinActivity);
    }

    public void call() {
        this.arg$1.getGuideFromServer();
    }
}
