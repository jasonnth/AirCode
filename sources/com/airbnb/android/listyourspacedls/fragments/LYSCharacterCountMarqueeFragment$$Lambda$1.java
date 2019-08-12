package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LYSCharacterCountMarqueeFragment$$Lambda$1 implements Action1 {
    private final LYSCharacterCountMarqueeFragment arg$1;

    private LYSCharacterCountMarqueeFragment$$Lambda$1(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        this.arg$1 = lYSCharacterCountMarqueeFragment;
    }

    public static Action1 lambdaFactory$(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        return new LYSCharacterCountMarqueeFragment$$Lambda$1(lYSCharacterCountMarqueeFragment);
    }

    public void call(Object obj) {
        LYSCharacterCountMarqueeFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
