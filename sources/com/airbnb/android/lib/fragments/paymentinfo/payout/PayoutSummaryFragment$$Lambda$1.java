package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.android.core.responses.GetExistingPayoutMethodResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PayoutSummaryFragment$$Lambda$1 implements Action1 {
    private final PayoutSummaryFragment arg$1;

    private PayoutSummaryFragment$$Lambda$1(PayoutSummaryFragment payoutSummaryFragment) {
        this.arg$1 = payoutSummaryFragment;
    }

    public static Action1 lambdaFactory$(PayoutSummaryFragment payoutSummaryFragment) {
        return new PayoutSummaryFragment$$Lambda$1(payoutSummaryFragment);
    }

    public void call(Object obj) {
        this.arg$1.payoutsAdapter.updateWithPayouts(((GetExistingPayoutMethodResponse) obj).paymentInstruments);
    }
}
