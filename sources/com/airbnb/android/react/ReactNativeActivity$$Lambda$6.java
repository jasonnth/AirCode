package com.airbnb.android.react;

final /* synthetic */ class ReactNativeActivity$$Lambda$6 implements Runnable {
    private final ReactNativeActivity arg$1;

    private ReactNativeActivity$$Lambda$6(ReactNativeActivity reactNativeActivity) {
        this.arg$1 = reactNativeActivity;
    }

    public static Runnable lambdaFactory$(ReactNativeActivity reactNativeActivity) {
        return new ReactNativeActivity$$Lambda$6(reactNativeActivity);
    }

    public void run() {
        this.arg$1.onFinishWaitingForRender(false);
    }
}
