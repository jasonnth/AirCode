package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class RedeemGiftCardFragment$$Lambda$5 implements Action1 {
    private final RedeemGiftCardFragment arg$1;

    private RedeemGiftCardFragment$$Lambda$5(RedeemGiftCardFragment redeemGiftCardFragment) {
        this.arg$1 = redeemGiftCardFragment;
    }

    public static Action1 lambdaFactory$(RedeemGiftCardFragment redeemGiftCardFragment) {
        return new RedeemGiftCardFragment$$Lambda$5(redeemGiftCardFragment);
    }

    public void call(Object obj) {
        RedeemGiftCardFragment.lambda$new$6(this.arg$1, (AirRequestNetworkException) obj);
    }
}
