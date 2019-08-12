package com.airbnb.android.core.utils;

import android.content.Context;

final /* synthetic */ class AppLaunchUtils$$Lambda$2 implements Runnable {
    private final AppLaunchUtils arg$1;
    private final Context arg$2;

    private AppLaunchUtils$$Lambda$2(AppLaunchUtils appLaunchUtils, Context context) {
        this.arg$1 = appLaunchUtils;
        this.arg$2 = context;
    }

    public static Runnable lambdaFactory$(AppLaunchUtils appLaunchUtils, Context context) {
        return new AppLaunchUtils$$Lambda$2(appLaunchUtils, context);
    }

    public void run() {
        this.arg$1.appStartStuffAlwaysDeferred(this.arg$2);
    }
}
