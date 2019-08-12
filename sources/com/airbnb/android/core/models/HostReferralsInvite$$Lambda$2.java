package com.airbnb.android.core.models;

import com.airbnb.android.core.models.ReferralContact.Phone;
import com.google.common.base.Function;

final /* synthetic */ class HostReferralsInvite$$Lambda$2 implements Function {
    private static final HostReferralsInvite$$Lambda$2 instance = new HostReferralsInvite$$Lambda$2();

    private HostReferralsInvite$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new HostReferralsInviteContact((Phone) obj);
    }
}
