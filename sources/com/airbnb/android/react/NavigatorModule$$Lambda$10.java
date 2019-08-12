package com.airbnb.android.react;

import android.app.Activity;
import android.content.Intent;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;

final /* synthetic */ class NavigatorModule$$Lambda$10 implements Runnable {
    private final Activity arg$1;
    private final ReadableMap arg$2;
    private final Intent arg$3;
    private final Promise arg$4;

    private NavigatorModule$$Lambda$10(Activity activity, ReadableMap readableMap, Intent intent, Promise promise) {
        this.arg$1 = activity;
        this.arg$2 = readableMap;
        this.arg$3 = intent;
        this.arg$4 = promise;
    }

    public static Runnable lambdaFactory$(Activity activity, ReadableMap readableMap, Intent intent, Promise promise) {
        return new NavigatorModule$$Lambda$10(activity, readableMap, intent, promise);
    }

    public void run() {
        NavigatorModule.lambda$startActivityWithPromise$7(this.arg$1, this.arg$2, this.arg$3, this.arg$4);
    }
}
