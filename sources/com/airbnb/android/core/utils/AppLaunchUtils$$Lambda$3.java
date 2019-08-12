package com.airbnb.android.core.utils;

import android.content.Context;

final /* synthetic */ class AppLaunchUtils$$Lambda$3 implements Runnable {
    private final AppLaunchUtils arg$1;
    private final Context arg$2;
    private final boolean arg$3;

    private AppLaunchUtils$$Lambda$3(AppLaunchUtils appLaunchUtils, Context context, boolean z) {
        this.arg$1 = appLaunchUtils;
        this.arg$2 = context;
        this.arg$3 = z;
    }

    public static Runnable lambdaFactory$(AppLaunchUtils appLaunchUtils, Context context, boolean z) {
        return new AppLaunchUtils$$Lambda$3(appLaunchUtils, context, z);
    }

    public void run() {
        AppLaunchUtils.lambda$doAppStartupStuffDeferred$2(this.arg$1, this.arg$2, this.arg$3);
    }
}
