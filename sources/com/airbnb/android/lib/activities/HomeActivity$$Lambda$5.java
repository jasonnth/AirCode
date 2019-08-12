package com.airbnb.android.lib.activities;

import com.airbnb.android.core.responses.GetTravelCouponResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HomeActivity$$Lambda$5 implements Action1 {
    private final HomeActivity arg$1;

    private HomeActivity$$Lambda$5(HomeActivity homeActivity) {
        this.arg$1 = homeActivity;
    }

    public static Action1 lambdaFactory$(HomeActivity homeActivity) {
        return new HomeActivity$$Lambda$5(homeActivity);
    }

    public void call(Object obj) {
        HomeActivity.lambda$new$8(this.arg$1, (GetTravelCouponResponse) obj);
    }
}
