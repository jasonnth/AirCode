package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingMakePrimaryHostFragment$$Lambda$2 implements Action1 {
    private final CohostingMakePrimaryHostFragment arg$1;

    private CohostingMakePrimaryHostFragment$$Lambda$2(CohostingMakePrimaryHostFragment cohostingMakePrimaryHostFragment) {
        this.arg$1 = cohostingMakePrimaryHostFragment;
    }

    public static Action1 lambdaFactory$(CohostingMakePrimaryHostFragment cohostingMakePrimaryHostFragment) {
        return new CohostingMakePrimaryHostFragment$$Lambda$2(cohostingMakePrimaryHostFragment);
    }

    public void call(Object obj) {
        this.arg$1.onError((AirRequestNetworkException) obj);
    }
}
