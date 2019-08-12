package com.airbnb.android.lib.fragments;

import android.widget.Toast;
import com.airbnb.android.lib.C0880R;
import p032rx.functions.Action1;

final /* synthetic */ class RedeemGiftCardFragment$$Lambda$7 implements Action1 {
    private final RedeemGiftCardFragment arg$1;

    private RedeemGiftCardFragment$$Lambda$7(RedeemGiftCardFragment redeemGiftCardFragment) {
        this.arg$1 = redeemGiftCardFragment;
    }

    public static Action1 lambdaFactory$(RedeemGiftCardFragment redeemGiftCardFragment) {
        return new RedeemGiftCardFragment$$Lambda$7(redeemGiftCardFragment);
    }

    public void call(Object obj) {
        Toast.makeText(this.arg$1.getActivity(), C0880R.string.gift_card_fetch_balance_error, 0).show();
    }
}
