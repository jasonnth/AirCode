package com.airbnb.android.payout;

import com.airbnb.android.payout.models.PayoutFormField;
import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.google.common.base.Function;

final /* synthetic */ class PayoutFormManager$$Lambda$1 implements Function {
    private static final PayoutFormManager$$Lambda$1 instance = new PayoutFormManager$$Lambda$1();

    private PayoutFormManager$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PayoutFormFieldInputWrapper.builder().payoutFormField((PayoutFormField) obj).hasValidationError(false).build();
    }
}
