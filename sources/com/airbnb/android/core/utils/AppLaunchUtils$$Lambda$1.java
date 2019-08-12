package com.airbnb.android.core.utils;

import android.app.Activity;
import android.content.Intent;

final /* synthetic */ class AppLaunchUtils$$Lambda$1 implements Runnable {
    private final AppLaunchUtils arg$1;
    private final Activity arg$2;
    private final Intent arg$3;

    private AppLaunchUtils$$Lambda$1(AppLaunchUtils appLaunchUtils, Activity activity, Intent intent) {
        this.arg$1 = appLaunchUtils;
        this.arg$2 = activity;
        this.arg$3 = intent;
    }

    public static Runnable lambdaFactory$(AppLaunchUtils appLaunchUtils, Activity activity, Intent intent) {
        return new AppLaunchUtils$$Lambda$1(appLaunchUtils, activity, intent);
    }

    public void run() {
        AppLaunchUtils.lambda$doTrack$0(this.arg$1, this.arg$2, this.arg$3);
    }
}
