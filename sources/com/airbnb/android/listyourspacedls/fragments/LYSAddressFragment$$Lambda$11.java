package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSAddressFragment$$Lambda$11 implements OnClickListener {
    private final LYSAddressFragment arg$1;

    private LYSAddressFragment$$Lambda$11(LYSAddressFragment lYSAddressFragment) {
        this.arg$1 = lYSAddressFragment;
    }

    public static OnClickListener lambdaFactory$(LYSAddressFragment lYSAddressFragment) {
        return new LYSAddressFragment$$Lambda$11(lYSAddressFragment);
    }

    public void onClick(View view) {
        this.arg$1.showTipDialog();
    }
}
