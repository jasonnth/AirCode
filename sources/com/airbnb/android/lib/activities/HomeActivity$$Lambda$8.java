package com.airbnb.android.lib.activities;

import android.os.Bundle;

final /* synthetic */ class HomeActivity$$Lambda$8 implements Runnable {
    private final HomeActivity arg$1;
    private final NavigationSection arg$2;
    private final Bundle arg$3;

    private HomeActivity$$Lambda$8(HomeActivity homeActivity, NavigationSection navigationSection, Bundle bundle) {
        this.arg$1 = homeActivity;
        this.arg$2 = navigationSection;
        this.arg$3 = bundle;
    }

    public static Runnable lambdaFactory$(HomeActivity homeActivity, NavigationSection navigationSection, Bundle bundle) {
        return new HomeActivity$$Lambda$8(homeActivity, navigationSection, bundle);
    }

    public void run() {
        HomeActivity.lambda$switchToMode$2(this.arg$1, this.arg$2, this.arg$3);
    }
}
