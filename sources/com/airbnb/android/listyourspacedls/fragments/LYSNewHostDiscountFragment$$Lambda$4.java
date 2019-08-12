package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LYSNewHostDiscountFragment$$Lambda$4 implements OnClickListener {
    private final LYSNewHostDiscountFragment arg$1;

    private LYSNewHostDiscountFragment$$Lambda$4(LYSNewHostDiscountFragment lYSNewHostDiscountFragment) {
        this.arg$1 = lYSNewHostDiscountFragment;
    }

    public static OnClickListener lambdaFactory$(LYSNewHostDiscountFragment lYSNewHostDiscountFragment) {
        return new LYSNewHostDiscountFragment$$Lambda$4(lYSNewHostDiscountFragment);
    }

    public void onClick(View view) {
        this.arg$1.showDiscountInfo();
    }
}
