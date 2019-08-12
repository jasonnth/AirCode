package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSAmenitiesFragment$$Lambda$4 implements OnClickListener {
    private final LYSAmenitiesFragment arg$1;

    private LYSAmenitiesFragment$$Lambda$4(LYSAmenitiesFragment lYSAmenitiesFragment) {
        this.arg$1 = lYSAmenitiesFragment;
    }

    public static OnClickListener lambdaFactory$(LYSAmenitiesFragment lYSAmenitiesFragment) {
        return new LYSAmenitiesFragment$$Lambda$4(lYSAmenitiesFragment);
    }

    public void onClick(View view) {
        this.arg$1.showTipPage();
    }
}
