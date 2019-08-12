package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.SetCohostingContractResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingShareEarningsWithFeeOptionFragment$$Lambda$2 implements Action1 {
    private final CohostingShareEarningsWithFeeOptionFragment arg$1;

    private CohostingShareEarningsWithFeeOptionFragment$$Lambda$2(CohostingShareEarningsWithFeeOptionFragment cohostingShareEarningsWithFeeOptionFragment) {
        this.arg$1 = cohostingShareEarningsWithFeeOptionFragment;
    }

    public static Action1 lambdaFactory$(CohostingShareEarningsWithFeeOptionFragment cohostingShareEarningsWithFeeOptionFragment) {
        return new CohostingShareEarningsWithFeeOptionFragment$$Lambda$2(cohostingShareEarningsWithFeeOptionFragment);
    }

    public void call(Object obj) {
        CohostingShareEarningsWithFeeOptionFragment.lambda$new$0(this.arg$1, (SetCohostingContractResponse) obj);
    }
}
