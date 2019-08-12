package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.ClaimGiftCardResponse;
import p032rx.functions.Action1;

final /* synthetic */ class RedeemGiftCardFragment$$Lambda$4 implements Action1 {
    private final RedeemGiftCardFragment arg$1;

    private RedeemGiftCardFragment$$Lambda$4(RedeemGiftCardFragment redeemGiftCardFragment) {
        this.arg$1 = redeemGiftCardFragment;
    }

    public static Action1 lambdaFactory$(RedeemGiftCardFragment redeemGiftCardFragment) {
        return new RedeemGiftCardFragment$$Lambda$4(redeemGiftCardFragment);
    }

    public void call(Object obj) {
        RedeemGiftCardFragment.lambda$new$5(this.arg$1, (ClaimGiftCardResponse) obj);
    }
}
