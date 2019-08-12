package com.airbnb.android.listyourspacedls.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class LYSCharacterCountMarqueeFragment$$Lambda$3 implements Action1 {
    private final LYSCharacterCountMarqueeFragment arg$1;

    private LYSCharacterCountMarqueeFragment$$Lambda$3(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        this.arg$1 = lYSCharacterCountMarqueeFragment;
    }

    public static Action1 lambdaFactory$(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        return new LYSCharacterCountMarqueeFragment$$Lambda$3(lYSCharacterCountMarqueeFragment);
    }

    public void call(Object obj) {
        LYSCharacterCountMarqueeFragment.lambda$new$2(this.arg$1, (Boolean) obj);
    }
}
