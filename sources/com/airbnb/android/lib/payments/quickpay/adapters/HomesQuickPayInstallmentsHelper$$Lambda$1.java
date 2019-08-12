package com.airbnb.android.lib.payments.quickpay.adapters;

import com.airbnb.android.core.models.Price;
import com.google.common.base.Function;

final /* synthetic */ class HomesQuickPayInstallmentsHelper$$Lambda$1 implements Function {
    private static final HomesQuickPayInstallmentsHelper$$Lambda$1 instance = new HomesQuickPayInstallmentsHelper$$Lambda$1();

    private HomesQuickPayInstallmentsHelper$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return HomesQuickPayInstallmentsHelper.lambda$updateInstallmentsModel$0((Price) obj);
    }
}
