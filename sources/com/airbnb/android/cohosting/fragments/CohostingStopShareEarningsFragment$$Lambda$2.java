package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingStopShareEarningsFragment$$Lambda$2 implements Action1 {
    private final CohostingStopShareEarningsFragment arg$1;

    private CohostingStopShareEarningsFragment$$Lambda$2(CohostingStopShareEarningsFragment cohostingStopShareEarningsFragment) {
        this.arg$1 = cohostingStopShareEarningsFragment;
    }

    public static Action1 lambdaFactory$(CohostingStopShareEarningsFragment cohostingStopShareEarningsFragment) {
        return new CohostingStopShareEarningsFragment$$Lambda$2(cohostingStopShareEarningsFragment);
    }

    public void call(Object obj) {
        CohostingStopShareEarningsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
