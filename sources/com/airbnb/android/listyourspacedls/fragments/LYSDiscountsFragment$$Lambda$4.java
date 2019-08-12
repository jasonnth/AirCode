package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSDiscountsFragment$$Lambda$4 implements OnClickListener {
    private final LYSDiscountsFragment arg$1;

    private LYSDiscountsFragment$$Lambda$4(LYSDiscountsFragment lYSDiscountsFragment) {
        this.arg$1 = lYSDiscountsFragment;
    }

    public static OnClickListener lambdaFactory$(LYSDiscountsFragment lYSDiscountsFragment) {
        return new LYSDiscountsFragment$$Lambda$4(lYSDiscountsFragment);
    }

    public void onClick(View view) {
        this.arg$1.showDisclaimer();
    }
}
