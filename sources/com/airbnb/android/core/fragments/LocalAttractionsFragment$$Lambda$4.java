package com.airbnb.android.core.fragments;

import com.airbnb.android.airmapview.listeners.OnMapInitializedListener;

final /* synthetic */ class LocalAttractionsFragment$$Lambda$4 implements OnMapInitializedListener {
    private final LocalAttractionsFragment arg$1;

    private LocalAttractionsFragment$$Lambda$4(LocalAttractionsFragment localAttractionsFragment) {
        this.arg$1 = localAttractionsFragment;
    }

    public static OnMapInitializedListener lambdaFactory$(LocalAttractionsFragment localAttractionsFragment) {
        return new LocalAttractionsFragment$$Lambda$4(localAttractionsFragment);
    }

    public void onMapInitialized() {
        LocalAttractionsFragment.lambda$setupMap$4(this.arg$1);
    }
}
