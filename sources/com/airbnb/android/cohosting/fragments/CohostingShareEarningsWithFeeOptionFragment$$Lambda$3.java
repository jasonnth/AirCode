package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingShareEarningsWithFeeOptionFragment$$Lambda$3 implements Action1 {
    private final CohostingShareEarningsWithFeeOptionFragment arg$1;

    private CohostingShareEarningsWithFeeOptionFragment$$Lambda$3(CohostingShareEarningsWithFeeOptionFragment cohostingShareEarningsWithFeeOptionFragment) {
        this.arg$1 = cohostingShareEarningsWithFeeOptionFragment;
    }

    public static Action1 lambdaFactory$(CohostingShareEarningsWithFeeOptionFragment cohostingShareEarningsWithFeeOptionFragment) {
        return new CohostingShareEarningsWithFeeOptionFragment$$Lambda$3(cohostingShareEarningsWithFeeOptionFragment);
    }

    public void call(Object obj) {
        CohostingShareEarningsWithFeeOptionFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
