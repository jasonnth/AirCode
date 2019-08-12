package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class RedeemGiftCardFragment$$Lambda$2 implements Action1 {
    private final RedeemGiftCardFragment arg$1;

    private RedeemGiftCardFragment$$Lambda$2(RedeemGiftCardFragment redeemGiftCardFragment) {
        this.arg$1 = redeemGiftCardFragment;
    }

    public static Action1 lambdaFactory$(RedeemGiftCardFragment redeemGiftCardFragment) {
        return new RedeemGiftCardFragment$$Lambda$2(redeemGiftCardFragment);
    }

    public void call(Object obj) {
        RedeemGiftCardFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
