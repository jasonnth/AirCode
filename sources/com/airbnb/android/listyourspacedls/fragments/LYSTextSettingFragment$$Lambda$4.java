package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.views.AirEditTextPageView.Listener;

final /* synthetic */ class LYSTextSettingFragment$$Lambda$4 implements Listener {
    private final LYSTextSettingFragment arg$1;

    private LYSTextSettingFragment$$Lambda$4(LYSTextSettingFragment lYSTextSettingFragment) {
        this.arg$1 = lYSTextSettingFragment;
    }

    public static Listener lambdaFactory$(LYSTextSettingFragment lYSTextSettingFragment) {
        return new LYSTextSettingFragment$$Lambda$4(lYSTextSettingFragment);
    }

    public void validityChanged(boolean z) {
        this.arg$1.updateSaveButton(z);
    }
}
