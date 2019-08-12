package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSCharacterCountMarqueeFragment$$Lambda$5 implements OnClickListener {
    private final LYSCharacterCountMarqueeFragment arg$1;

    private LYSCharacterCountMarqueeFragment$$Lambda$5(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        this.arg$1 = lYSCharacterCountMarqueeFragment;
    }

    public static OnClickListener lambdaFactory$(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment) {
        return new LYSCharacterCountMarqueeFragment$$Lambda$5(lYSCharacterCountMarqueeFragment);
    }

    public void onClick(View view) {
        this.arg$1.controller.showTipModal(this.arg$1.setting.tipTitleRes, this.arg$1.setting.tipTextRes, this.arg$1.setting.tipNavigationTag);
    }
}
