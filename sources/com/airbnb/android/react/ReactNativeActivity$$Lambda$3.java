package com.airbnb.android.react;

final /* synthetic */ class ReactNativeActivity$$Lambda$3 implements Runnable {
    private final ReactNativeActivity arg$1;

    private ReactNativeActivity$$Lambda$3(ReactNativeActivity reactNativeActivity) {
        this.arg$1 = reactNativeActivity;
    }

    public static Runnable lambdaFactory$(ReactNativeActivity reactNativeActivity) {
        return new ReactNativeActivity$$Lambda$3(reactNativeActivity);
    }

    public void run() {
        this.arg$1.emitEvent(ReactNativeActivity.ON_ENTER_TRANSITION_COMPLETE, null);
    }
}
