package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HomeActivity$$Lambda$9 implements OnClickListener {
    private final HomeActivity arg$1;

    private HomeActivity$$Lambda$9(HomeActivity homeActivity) {
        this.arg$1 = homeActivity;
    }

    public static OnClickListener lambdaFactory$(HomeActivity homeActivity) {
        return new HomeActivity$$Lambda$9(homeActivity);
    }

    public void onClick(View view) {
        HomeActivity.lambda$onLowBandwidthActivated$3(this.arg$1, view);
    }
}
