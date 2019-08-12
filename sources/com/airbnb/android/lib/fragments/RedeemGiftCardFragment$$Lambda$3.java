package com.airbnb.android.lib.fragments;

import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class RedeemGiftCardFragment$$Lambda$3 implements StringTextWatcher {
    private final RedeemGiftCardFragment arg$1;

    private RedeemGiftCardFragment$$Lambda$3(RedeemGiftCardFragment redeemGiftCardFragment) {
        this.arg$1 = redeemGiftCardFragment;
    }

    public static StringTextWatcher lambdaFactory$(RedeemGiftCardFragment redeemGiftCardFragment) {
        return new RedeemGiftCardFragment$$Lambda$3(redeemGiftCardFragment);
    }

    public void textUpdated(String str) {
        RedeemGiftCardFragment.lambda$new$4(this.arg$1, str);
    }
}
