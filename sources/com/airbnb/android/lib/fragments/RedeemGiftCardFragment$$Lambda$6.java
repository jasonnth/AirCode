package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.GiftCreditBalanceResponse;
import p032rx.functions.Action1;

final /* synthetic */ class RedeemGiftCardFragment$$Lambda$6 implements Action1 {
    private final RedeemGiftCardFragment arg$1;

    private RedeemGiftCardFragment$$Lambda$6(RedeemGiftCardFragment redeemGiftCardFragment) {
        this.arg$1 = redeemGiftCardFragment;
    }

    public static Action1 lambdaFactory$(RedeemGiftCardFragment redeemGiftCardFragment) {
        return new RedeemGiftCardFragment$$Lambda$6(redeemGiftCardFragment);
    }

    public void call(Object obj) {
        RedeemGiftCardFragment.lambda$new$7(this.arg$1, (GiftCreditBalanceResponse) obj);
    }
}
