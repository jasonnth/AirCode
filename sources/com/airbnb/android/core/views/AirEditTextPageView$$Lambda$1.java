package com.airbnb.android.core.views;

import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class AirEditTextPageView$$Lambda$1 implements StringTextWatcher {
    private final AirEditTextPageView arg$1;

    private AirEditTextPageView$$Lambda$1(AirEditTextPageView airEditTextPageView) {
        this.arg$1 = airEditTextPageView;
    }

    public static StringTextWatcher lambdaFactory$(AirEditTextPageView airEditTextPageView) {
        return new AirEditTextPageView$$Lambda$1(airEditTextPageView);
    }

    public void textUpdated(String str) {
        this.arg$1.validateTextLengthUpdateHint();
    }
}
