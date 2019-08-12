package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.DeleteCohostingContractResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingStopShareEarningsFragment$$Lambda$1 implements Action1 {
    private final CohostingStopShareEarningsFragment arg$1;

    private CohostingStopShareEarningsFragment$$Lambda$1(CohostingStopShareEarningsFragment cohostingStopShareEarningsFragment) {
        this.arg$1 = cohostingStopShareEarningsFragment;
    }

    public static Action1 lambdaFactory$(CohostingStopShareEarningsFragment cohostingStopShareEarningsFragment) {
        return new CohostingStopShareEarningsFragment$$Lambda$1(cohostingStopShareEarningsFragment);
    }

    public void call(Object obj) {
        CohostingStopShareEarningsFragment.lambda$new$0(this.arg$1, (DeleteCohostingContractResponse) obj);
    }
}
