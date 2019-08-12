package com.airbnb.android.react;

final /* synthetic */ class ReactNativeFragment$$Lambda$2 implements OnMenuButtonClickListener {
    private final ReactNativeFragment arg$1;

    private ReactNativeFragment$$Lambda$2(ReactNativeFragment reactNativeFragment) {
        this.arg$1 = reactNativeFragment;
    }

    public static OnMenuButtonClickListener lambdaFactory$(ReactNativeFragment reactNativeFragment) {
        return new ReactNativeFragment$$Lambda$2(reactNativeFragment);
    }

    public void onClick(MenuButton menuButton, int i) {
        this.arg$1.emitEvent(ReactNativeFragment.ON_BUTTON_PRESS, Integer.valueOf(i));
    }
}
