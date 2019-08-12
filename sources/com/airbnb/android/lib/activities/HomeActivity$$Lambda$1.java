package com.airbnb.android.lib.activities;

import com.roughike.bottombar.OnTabSelectListener;

final /* synthetic */ class HomeActivity$$Lambda$1 implements OnTabSelectListener {
    private final HomeActivity arg$1;

    private HomeActivity$$Lambda$1(HomeActivity homeActivity) {
        this.arg$1 = homeActivity;
    }

    public static OnTabSelectListener lambdaFactory$(HomeActivity homeActivity) {
        return new HomeActivity$$Lambda$1(homeActivity);
    }

    public void onTabSelected(int i) {
        HomeActivity.lambda$new$1(this.arg$1, i);
    }
}
