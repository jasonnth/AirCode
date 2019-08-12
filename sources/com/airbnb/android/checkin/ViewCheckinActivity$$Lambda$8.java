package com.airbnb.android.checkin;

import com.airbnb.android.core.models.CheckInGuide;
import p032rx.functions.Action1;

final /* synthetic */ class ViewCheckinActivity$$Lambda$8 implements Action1 {
    private final ViewCheckinActivity arg$1;

    private ViewCheckinActivity$$Lambda$8(ViewCheckinActivity viewCheckinActivity) {
        this.arg$1 = viewCheckinActivity;
    }

    public static Action1 lambdaFactory$(ViewCheckinActivity viewCheckinActivity) {
        return new ViewCheckinActivity$$Lambda$8(viewCheckinActivity);
    }

    public void call(Object obj) {
        ViewCheckinActivity.lambda$getGuideFromDatabase$9(this.arg$1, (CheckInGuide) obj);
    }
}
