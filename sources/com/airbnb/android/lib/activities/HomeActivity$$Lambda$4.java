package com.airbnb.android.lib.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class HomeActivity$$Lambda$4 implements Action1 {
    private final HomeActivity arg$1;

    private HomeActivity$$Lambda$4(HomeActivity homeActivity) {
        this.arg$1 = homeActivity;
    }

    public static Action1 lambdaFactory$(HomeActivity homeActivity) {
        return new HomeActivity$$Lambda$4(homeActivity);
    }

    public void call(Object obj) {
        HomeActivity.lambda$new$7(this.arg$1, (AirRequestNetworkException) obj);
    }
}
