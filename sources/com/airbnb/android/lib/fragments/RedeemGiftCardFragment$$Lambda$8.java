package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class RedeemGiftCardFragment$$Lambda$8 implements OnFocusChangeListener {
    private final RedeemGiftCardFragment arg$1;

    private RedeemGiftCardFragment$$Lambda$8(RedeemGiftCardFragment redeemGiftCardFragment) {
        this.arg$1 = redeemGiftCardFragment;
    }

    public static OnFocusChangeListener lambdaFactory$(RedeemGiftCardFragment redeemGiftCardFragment) {
        return new RedeemGiftCardFragment$$Lambda$8(redeemGiftCardFragment);
    }

    public void onFocusChange(View view, boolean z) {
        RedeemGiftCardFragment.lambda$initializeGiftCardCodeInput$2(this.arg$1, view, z);
    }
}
