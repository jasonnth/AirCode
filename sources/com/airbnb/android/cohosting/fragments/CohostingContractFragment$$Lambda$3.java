package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingContractFragment$$Lambda$3 implements Action1 {
    private final CohostingContractFragment arg$1;

    private CohostingContractFragment$$Lambda$3(CohostingContractFragment cohostingContractFragment) {
        this.arg$1 = cohostingContractFragment;
    }

    public static Action1 lambdaFactory$(CohostingContractFragment cohostingContractFragment) {
        return new CohostingContractFragment$$Lambda$3(cohostingContractFragment);
    }

    public void call(Object obj) {
        CohostingContractFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
