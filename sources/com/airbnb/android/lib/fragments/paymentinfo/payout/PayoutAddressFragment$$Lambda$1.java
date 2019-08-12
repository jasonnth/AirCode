package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.android.core.responses.PayoutInfoTypesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PayoutAddressFragment$$Lambda$1 implements Action1 {
    private final PayoutAddressFragment arg$1;

    private PayoutAddressFragment$$Lambda$1(PayoutAddressFragment payoutAddressFragment) {
        this.arg$1 = payoutAddressFragment;
    }

    public static Action1 lambdaFactory$(PayoutAddressFragment payoutAddressFragment) {
        return new PayoutAddressFragment$$Lambda$1(payoutAddressFragment);
    }

    public void call(Object obj) {
        PayoutAddressFragment.lambda$new$1(this.arg$1, (PayoutInfoTypesResponse) obj);
    }
}
