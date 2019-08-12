package com.airbnb.android.lib.activities;

import com.airbnb.android.core.responses.AccountResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HomeActivity$$Lambda$2 implements Action1 {
    private final HomeActivity arg$1;

    private HomeActivity$$Lambda$2(HomeActivity homeActivity) {
        this.arg$1 = homeActivity;
    }

    public static Action1 lambdaFactory$(HomeActivity homeActivity) {
        return new HomeActivity$$Lambda$2(homeActivity);
    }

    public void call(Object obj) {
        HomeActivity.lambda$new$4(this.arg$1, (AccountResponse) obj);
    }
}
