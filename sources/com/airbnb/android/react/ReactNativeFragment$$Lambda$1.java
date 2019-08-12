package com.airbnb.android.react;

import p032rx.functions.Action0;

final /* synthetic */ class ReactNativeFragment$$Lambda$1 implements Action0 {
    private final ReactNativeFragment arg$1;

    private ReactNativeFragment$$Lambda$1(ReactNativeFragment reactNativeFragment) {
        this.arg$1 = reactNativeFragment;
    }

    public static Action0 lambdaFactory$(ReactNativeFragment reactNativeFragment) {
        return new ReactNativeFragment$$Lambda$1(reactNativeFragment);
    }

    public void call() {
        this.arg$1.onCreateWithReactContext();
    }
}
