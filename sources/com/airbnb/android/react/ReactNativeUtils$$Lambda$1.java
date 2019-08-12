package com.airbnb.android.react;

import android.app.Application;

final /* synthetic */ class ReactNativeUtils$$Lambda$1 implements Runnable {
    private final Application arg$1;

    private ReactNativeUtils$$Lambda$1(Application application) {
        this.arg$1 = application;
    }

    public static Runnable lambdaFactory$(Application application) {
        return new ReactNativeUtils$$Lambda$1(application);
    }

    public void run() {
        ReactNativeUtils.lambda$handleOverlayPermissionsMissing$0(this.arg$1);
    }
}
