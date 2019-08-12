package com.airbnb.android.core.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class DLSHouseRulesFragment$$Lambda$2 implements Action1 {
    private final DLSHouseRulesFragment arg$1;

    private DLSHouseRulesFragment$$Lambda$2(DLSHouseRulesFragment dLSHouseRulesFragment) {
        this.arg$1 = dLSHouseRulesFragment;
    }

    public static Action1 lambdaFactory$(DLSHouseRulesFragment dLSHouseRulesFragment) {
        return new DLSHouseRulesFragment$$Lambda$2(dLSHouseRulesFragment);
    }

    public void call(Object obj) {
        DLSHouseRulesFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
