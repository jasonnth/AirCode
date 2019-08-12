package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class PayoutSummaryFragment$$Lambda$2 implements Action1 {
    private final PayoutSummaryFragment arg$1;

    private PayoutSummaryFragment$$Lambda$2(PayoutSummaryFragment payoutSummaryFragment) {
        this.arg$1 = payoutSummaryFragment;
    }

    public static Action1 lambdaFactory$(PayoutSummaryFragment payoutSummaryFragment) {
        return new PayoutSummaryFragment$$Lambda$2(payoutSummaryFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
