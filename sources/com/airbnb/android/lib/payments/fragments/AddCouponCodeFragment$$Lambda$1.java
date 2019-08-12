package com.airbnb.android.lib.payments.fragments;

import com.airbnb.android.core.models.PriceQuote;
import p032rx.functions.Action1;

final /* synthetic */ class AddCouponCodeFragment$$Lambda$1 implements Action1 {
    private final AddCouponCodeFragment arg$1;

    private AddCouponCodeFragment$$Lambda$1(AddCouponCodeFragment addCouponCodeFragment) {
        this.arg$1 = addCouponCodeFragment;
    }

    public static Action1 lambdaFactory$(AddCouponCodeFragment addCouponCodeFragment) {
        return new AddCouponCodeFragment$$Lambda$1(addCouponCodeFragment);
    }

    public void call(Object obj) {
        this.arg$1.onCouponCodeApplied(((PriceQuote) obj).getBillPriceQuote());
    }
}
