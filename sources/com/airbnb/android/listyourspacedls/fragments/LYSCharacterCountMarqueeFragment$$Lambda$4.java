package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.android.core.views.AirEditTextPageView.Listener;

final /* synthetic */ class LYSCharacterCountMarqueeFragment$$Lambda$4 implements Listener {
    private final LYSCharacterCountMarqueeFragment arg$1;

    private LYSCharacterCountMarqueeFragment$$Lambda$4(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        this.arg$1 = lYSCharacterCountMarqueeFragment;
    }

    public static Listener lambdaFactory$(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        return new LYSCharacterCountMarqueeFragment$$Lambda$4(lYSCharacterCountMarqueeFragment);
    }

    public void validityChanged(boolean z) {
        this.arg$1.updateButtons(z);
    }
}
