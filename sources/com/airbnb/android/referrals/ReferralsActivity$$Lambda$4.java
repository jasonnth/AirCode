package com.airbnb.android.referrals;

import java.util.ArrayList;
import p032rx.functions.Action1;

final /* synthetic */ class ReferralsActivity$$Lambda$4 implements Action1 {
    private final ReferralsActivity arg$1;

    private ReferralsActivity$$Lambda$4(ReferralsActivity referralsActivity) {
        this.arg$1 = referralsActivity;
    }

    public static Action1 lambdaFactory$(ReferralsActivity referralsActivity) {
        return new ReferralsActivity$$Lambda$4(referralsActivity);
    }

    public void call(Object obj) {
        this.arg$1.grayUsers = new ArrayList<>();
    }
}
