package com.airbnb.android.checkin;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ViewCheckinActivity$$Lambda$2 implements Action1 {
    private final ViewCheckinActivity arg$1;

    private ViewCheckinActivity$$Lambda$2(ViewCheckinActivity viewCheckinActivity) {
        this.arg$1 = viewCheckinActivity;
    }

    public static Action1 lambdaFactory$(ViewCheckinActivity viewCheckinActivity) {
        return new ViewCheckinActivity$$Lambda$2(viewCheckinActivity);
    }

    public void call(Object obj) {
        ViewCheckinActivity.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
