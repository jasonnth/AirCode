package com.airbnb.android.core.models;

import com.airbnb.android.core.models.ReferralContact.Email;
import com.google.common.base.Function;

final /* synthetic */ class ReferralContact$$Lambda$3 implements Function {
    private static final ReferralContact$$Lambda$3 instance = new ReferralContact$$Lambda$3();

    private ReferralContact$$Lambda$3() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((Email) obj).getValue();
    }
}
