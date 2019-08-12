package com.airbnb.android.lib.activities;

import com.airbnb.android.lib.responses.ChinaCampaignCouponClaimResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HomeActivity$$Lambda$6 implements Action1 {
    private final HomeActivity arg$1;

    private HomeActivity$$Lambda$6(HomeActivity homeActivity) {
        this.arg$1 = homeActivity;
    }

    public static Action1 lambdaFactory$(HomeActivity homeActivity) {
        return new HomeActivity$$Lambda$6(homeActivity);
    }

    public void call(Object obj) {
        HomeActivity.lambda$new$9(this.arg$1, (ChinaCampaignCouponClaimResponse) obj);
    }
}
