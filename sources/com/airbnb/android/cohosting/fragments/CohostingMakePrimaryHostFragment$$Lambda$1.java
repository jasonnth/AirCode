package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingMakePrimaryHostFragment$$Lambda$1 implements Action1 {
    private final CohostingMakePrimaryHostFragment arg$1;

    private CohostingMakePrimaryHostFragment$$Lambda$1(CohostingMakePrimaryHostFragment cohostingMakePrimaryHostFragment) {
        this.arg$1 = cohostingMakePrimaryHostFragment;
    }

    public static Action1 lambdaFactory$(CohostingMakePrimaryHostFragment cohostingMakePrimaryHostFragment) {
        return new CohostingMakePrimaryHostFragment$$Lambda$1(cohostingMakePrimaryHostFragment);
    }

    public void call(Object obj) {
        this.arg$1.responseFinished((AirBatchResponse) obj);
    }
}
