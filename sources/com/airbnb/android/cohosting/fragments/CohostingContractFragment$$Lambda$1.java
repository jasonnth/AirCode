package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.cohosting.adapters.CohostingContractAdapter.Listener;

final /* synthetic */ class CohostingContractFragment$$Lambda$1 implements Listener {
    private final CohostingContractFragment arg$1;

    private CohostingContractFragment$$Lambda$1(CohostingContractFragment cohostingContractFragment) {
        this.arg$1 = cohostingContractFragment;
    }

    public static Listener lambdaFactory$(CohostingContractFragment cohostingContractFragment) {
        return new CohostingContractFragment$$Lambda$1(cohostingContractFragment);
    }

    public void logImpression() {
        this.arg$1.logImpression();
    }
}
