package com.airbnb.android.payout.create;

import com.airbnb.android.payout.models.PayoutInfoForm;
import com.google.common.base.Function;

final /* synthetic */ class AddPayoutMethodUtils$$Lambda$1 implements Function {
    private static final AddPayoutMethodUtils$$Lambda$1 instance = new AddPayoutMethodUtils$$Lambda$1();

    private AddPayoutMethodUtils$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((PayoutInfoForm) obj).currencies();
    }
}
