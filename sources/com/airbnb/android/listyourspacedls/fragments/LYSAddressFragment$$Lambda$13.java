package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSAddressFragment$$Lambda$13 implements OnClickListener {
    private final LYSAddressFragment arg$1;

    private LYSAddressFragment$$Lambda$13(LYSAddressFragment lYSAddressFragment) {
        this.arg$1 = lYSAddressFragment;
    }

    public static OnClickListener lambdaFactory$(LYSAddressFragment lYSAddressFragment) {
        return new LYSAddressFragment$$Lambda$13(lYSAddressFragment);
    }

    public void onClick(View view) {
        this.arg$1.executeBedTypeRequests();
    }
}
