package com.airbnb.android.react;

final /* synthetic */ class ReactNativeActivity$$Lambda$7 implements OnMenuButtonClickListener {
    private final ReactNativeActivity arg$1;

    private ReactNativeActivity$$Lambda$7(ReactNativeActivity reactNativeActivity) {
        this.arg$1 = reactNativeActivity;
    }

    public static OnMenuButtonClickListener lambdaFactory$(ReactNativeActivity reactNativeActivity) {
        return new ReactNativeActivity$$Lambda$7(reactNativeActivity);
    }

    public void onClick(MenuButton menuButton, int i) {
        this.arg$1.emitEvent(ReactNativeActivity.ON_BUTTON_PRESS, Integer.valueOf(i));
    }
}
