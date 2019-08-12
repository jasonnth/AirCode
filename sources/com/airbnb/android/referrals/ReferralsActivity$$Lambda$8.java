package com.airbnb.android.referrals;

import com.airbnb.android.core.responses.GetUserReferralResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ReferralsActivity$$Lambda$8 implements Action1 {
    private final ReferralsActivity arg$1;

    private ReferralsActivity$$Lambda$8(ReferralsActivity referralsActivity) {
        this.arg$1 = referralsActivity;
    }

    public static Action1 lambdaFactory$(ReferralsActivity referralsActivity) {
        return new ReferralsActivity$$Lambda$8(referralsActivity);
    }

    public void call(Object obj) {
        ReferralsActivity.lambda$new$5(this.arg$1, (GetUserReferralResponse) obj);
    }
}
