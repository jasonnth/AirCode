package com.airbnb.android.core.models;

import com.airbnb.android.core.models.ReferralContact.Phone;
import com.google.common.base.Function;

final /* synthetic */ class ReferralContact$$Lambda$2 implements Function {
    private static final ReferralContact$$Lambda$2 instance = new ReferralContact$$Lambda$2();

    private ReferralContact$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((Phone) obj).getValue();
    }
}
