package com.airbnb.android.referrals;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ReferralsActivity$$Lambda$9 implements Action1 {
    private final ReferralsActivity arg$1;

    private ReferralsActivity$$Lambda$9(ReferralsActivity referralsActivity) {
        this.arg$1 = referralsActivity;
    }

    public static Action1 lambdaFactory$(ReferralsActivity referralsActivity) {
        return new ReferralsActivity$$Lambda$9(referralsActivity);
    }

    public void call(Object obj) {
        ReferralsActivity.lambda$new$6(this.arg$1, (AirRequestNetworkException) obj);
    }
}
