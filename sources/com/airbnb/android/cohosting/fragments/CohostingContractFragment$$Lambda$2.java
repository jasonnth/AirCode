package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.CohostingContractResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingContractFragment$$Lambda$2 implements Action1 {
    private final CohostingContractFragment arg$1;

    private CohostingContractFragment$$Lambda$2(CohostingContractFragment cohostingContractFragment) {
        this.arg$1 = cohostingContractFragment;
    }

    public static Action1 lambdaFactory$(CohostingContractFragment cohostingContractFragment) {
        return new CohostingContractFragment$$Lambda$2(cohostingContractFragment);
    }

    public void call(Object obj) {
        CohostingContractFragment.lambda$new$0(this.arg$1, (CohostingContractResponse) obj);
    }
}
