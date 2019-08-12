package com.airbnb.android.core.views;

import com.airbnb.android.utils.KeyboardUtils;

final /* synthetic */ class AirEditTextPageView$$Lambda$2 implements Runnable {
    private final AirEditTextPageView arg$1;

    private AirEditTextPageView$$Lambda$2(AirEditTextPageView airEditTextPageView) {
        this.arg$1 = airEditTextPageView;
    }

    public static Runnable lambdaFactory$(AirEditTextPageView airEditTextPageView) {
        return new AirEditTextPageView$$Lambda$2(airEditTextPageView);
    }

    public void run() {
        KeyboardUtils.showSoftKeyboard(this.arg$1.textView);
    }
}
