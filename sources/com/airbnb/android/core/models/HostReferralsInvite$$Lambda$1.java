package com.airbnb.android.core.models;

import com.airbnb.android.core.models.ReferralContact.Email;
import com.google.common.base.Function;

final /* synthetic */ class HostReferralsInvite$$Lambda$1 implements Function {
    private static final HostReferralsInvite$$Lambda$1 instance = new HostReferralsInvite$$Lambda$1();

    private HostReferralsInvite$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new HostReferralsInviteContact((Email) obj);
    }
}
