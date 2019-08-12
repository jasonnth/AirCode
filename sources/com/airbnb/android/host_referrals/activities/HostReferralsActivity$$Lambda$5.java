package com.airbnb.android.host_referrals.activities;

import com.airbnb.android.core.responses.GetHostReferralInfoResponse;
import p032rx.functions.Action1;

final /* synthetic */ class HostReferralsActivity$$Lambda$5 implements Action1 {
    private final HostReferralsActivity arg$1;

    private HostReferralsActivity$$Lambda$5(HostReferralsActivity hostReferralsActivity) {
        this.arg$1 = hostReferralsActivity;
    }

    public static Action1 lambdaFactory$(HostReferralsActivity hostReferralsActivity) {
        return new HostReferralsActivity$$Lambda$5(hostReferralsActivity);
    }

    public void call(Object obj) {
        HostReferralsActivity.lambda$new$2(this.arg$1, (GetHostReferralInfoResponse) obj);
    }
}
