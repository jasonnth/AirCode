package com.airbnb.android.lib.payments.quickpay.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GiftCardQuickPayFragment$$Lambda$2 implements OnClickListener {
    private final GiftCardQuickPayFragment arg$1;

    private GiftCardQuickPayFragment$$Lambda$2(GiftCardQuickPayFragment giftCardQuickPayFragment) {
        this.arg$1 = giftCardQuickPayFragment;
    }

    public static OnClickListener lambdaFactory$(GiftCardQuickPayFragment giftCardQuickPayFragment) {
        return new GiftCardQuickPayFragment$$Lambda$2(giftCardQuickPayFragment);
    }

    public void onClick(View view) {
        GiftCardQuickPayFragment.lambda$payButtonClickListener$1(this.arg$1, view);
    }
}
