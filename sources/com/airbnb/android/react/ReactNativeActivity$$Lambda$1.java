package com.airbnb.android.react;

import p032rx.functions.Action0;

final /* synthetic */ class ReactNativeActivity$$Lambda$1 implements Action0 {
    private final ReactNativeActivity arg$1;

    private ReactNativeActivity$$Lambda$1(ReactNativeActivity reactNativeActivity) {
        this.arg$1 = reactNativeActivity;
    }

    public static Action0 lambdaFactory$(ReactNativeActivity reactNativeActivity) {
        return new ReactNativeActivity$$Lambda$1(reactNativeActivity);
    }

    public void call() {
        this.arg$1.onCreateWithReactContext();
    }
}
