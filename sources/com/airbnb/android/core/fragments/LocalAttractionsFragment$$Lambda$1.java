package com.airbnb.android.core.fragments;

import com.airbnb.android.core.responses.LocalAttractionsResponse;
import java.util.ArrayList;
import p032rx.functions.Action1;

final /* synthetic */ class LocalAttractionsFragment$$Lambda$1 implements Action1 {
    private final LocalAttractionsFragment arg$1;

    private LocalAttractionsFragment$$Lambda$1(LocalAttractionsFragment localAttractionsFragment) {
        this.arg$1 = localAttractionsFragment;
    }

    public static Action1 lambdaFactory$(LocalAttractionsFragment localAttractionsFragment) {
        return new LocalAttractionsFragment$$Lambda$1(localAttractionsFragment);
    }

    public void call(Object obj) {
        this.arg$1.setupAttractions((ArrayList) ((LocalAttractionsResponse) obj).localAttractions);
    }
}
