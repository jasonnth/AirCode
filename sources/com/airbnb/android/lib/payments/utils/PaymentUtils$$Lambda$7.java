package com.airbnb.android.lib.payments.utils;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.google.common.base.Function;

final /* synthetic */ class PaymentUtils$$Lambda$7 implements Function {
    private static final PaymentUtils$$Lambda$7 instance = new PaymentUtils$$Lambda$7();

    private PaymentUtils$$Lambda$7() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PaymentMethodType.findByServerKey(((PaymentOption) obj).getPaymentMethodType());
    }
}
