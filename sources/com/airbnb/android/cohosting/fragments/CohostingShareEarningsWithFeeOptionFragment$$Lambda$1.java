package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.cohosting.epoxycontrollers.CohostingShareEarningsEpoxyController.Listener;

final /* synthetic */ class CohostingShareEarningsWithFeeOptionFragment$$Lambda$1 implements Listener {
    private final CohostingShareEarningsWithFeeOptionFragment arg$1;

    private CohostingShareEarningsWithFeeOptionFragment$$Lambda$1(CohostingShareEarningsWithFeeOptionFragment cohostingShareEarningsWithFeeOptionFragment) {
        this.arg$1 = cohostingShareEarningsWithFeeOptionFragment;
    }

    public static Listener lambdaFactory$(CohostingShareEarningsWithFeeOptionFragment cohostingShareEarningsWithFeeOptionFragment) {
        return new CohostingShareEarningsWithFeeOptionFragment$$Lambda$1(cohostingShareEarningsWithFeeOptionFragment);
    }

    public void enableShareButton(boolean z) {
        this.arg$1.enableShareButton(z);
    }
}
