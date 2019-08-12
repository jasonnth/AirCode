package com.airbnb.android.lib.identity.psb;

import com.airbnb.android.utils.TextWatcherUtils.IsEmptyTextWatcher;

final /* synthetic */ class InputIdentificationNumberFragment$$Lambda$1 implements IsEmptyTextWatcher {
    private final InputIdentificationNumberFragment arg$1;

    private InputIdentificationNumberFragment$$Lambda$1(InputIdentificationNumberFragment inputIdentificationNumberFragment) {
        this.arg$1 = inputIdentificationNumberFragment;
    }

    public static IsEmptyTextWatcher lambdaFactory$(InputIdentificationNumberFragment inputIdentificationNumberFragment) {
        return new InputIdentificationNumberFragment$$Lambda$1(inputIdentificationNumberFragment);
    }

    public void textUpdated(boolean z) {
        this.arg$1.textWatch(z);
    }
}
