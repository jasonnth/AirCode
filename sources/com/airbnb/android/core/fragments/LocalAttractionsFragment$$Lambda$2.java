package com.airbnb.android.core.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LocalAttractionsFragment$$Lambda$2 implements Action1 {
    private final LocalAttractionsFragment arg$1;

    private LocalAttractionsFragment$$Lambda$2(LocalAttractionsFragment localAttractionsFragment) {
        this.arg$1 = localAttractionsFragment;
    }

    public static Action1 lambdaFactory$(LocalAttractionsFragment localAttractionsFragment) {
        return new LocalAttractionsFragment$$Lambda$2(localAttractionsFragment);
    }

    public void call(Object obj) {
        LocalAttractionsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
