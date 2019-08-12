package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSHouseRulesFragment$$Lambda$2 implements Action1 {
    private final LYSHouseRulesFragment arg$1;

    private LYSHouseRulesFragment$$Lambda$2(LYSHouseRulesFragment lYSHouseRulesFragment) {
        this.arg$1 = lYSHouseRulesFragment;
    }

    public static Action1 lambdaFactory$(LYSHouseRulesFragment lYSHouseRulesFragment) {
        return new LYSHouseRulesFragment$$Lambda$2(lYSHouseRulesFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
