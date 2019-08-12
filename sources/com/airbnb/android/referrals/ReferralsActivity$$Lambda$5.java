package com.airbnb.android.referrals;

import p032rx.functions.Action1;

final /* synthetic */ class ReferralsActivity$$Lambda$5 implements Action1 {
    private final ReferralsActivity arg$1;

    private ReferralsActivity$$Lambda$5(ReferralsActivity referralsActivity) {
        this.arg$1 = referralsActivity;
    }

    public static Action1 lambdaFactory$(ReferralsActivity referralsActivity) {
        return new ReferralsActivity$$Lambda$5(referralsActivity);
    }

    public void call(Object obj) {
        this.arg$1.onDataLoaded();
    }
}
