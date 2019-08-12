package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSSpaceTypeFragment$$Lambda$4 implements OnClickListener {
    private final LYSSpaceTypeFragment arg$1;

    private LYSSpaceTypeFragment$$Lambda$4(LYSSpaceTypeFragment lYSSpaceTypeFragment) {
        this.arg$1 = lYSSpaceTypeFragment;
    }

    public static OnClickListener lambdaFactory$(LYSSpaceTypeFragment lYSSpaceTypeFragment) {
        return new LYSSpaceTypeFragment$$Lambda$4(lYSSpaceTypeFragment);
    }

    public void onClick(View view) {
        this.arg$1.showTipModal();
    }
}
