package com.airbnb.android.react;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

final /* synthetic */ class ReactNativeActivity$$Lambda$2 implements OnDismissListener {
    private final ReactNativeActivity arg$1;

    private ReactNativeActivity$$Lambda$2(ReactNativeActivity reactNativeActivity) {
        this.arg$1 = reactNativeActivity;
    }

    public static OnDismissListener lambdaFactory$(ReactNativeActivity reactNativeActivity) {
        return new ReactNativeActivity$$Lambda$2(reactNativeActivity);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.arg$1.finish();
    }
}
