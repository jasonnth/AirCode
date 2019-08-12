package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSTextSettingFragment$$Lambda$2 implements Action1 {
    private final LYSTextSettingFragment arg$1;

    private LYSTextSettingFragment$$Lambda$2(LYSTextSettingFragment lYSTextSettingFragment) {
        this.arg$1 = lYSTextSettingFragment;
    }

    public static Action1 lambdaFactory$(LYSTextSettingFragment lYSTextSettingFragment) {
        return new LYSTextSettingFragment$$Lambda$2(lYSTextSettingFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
