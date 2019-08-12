package com.airbnb.android.payout;

import com.airbnb.android.payout.models.PayoutFormField;
import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.google.common.base.Predicate;

final /* synthetic */ class PayoutFormManager$$Lambda$2 implements Predicate {
    private final PayoutFormField arg$1;

    private PayoutFormManager$$Lambda$2(PayoutFormField payoutFormField) {
        this.arg$1 = payoutFormField;
    }

    public static Predicate lambdaFactory$(PayoutFormField payoutFormField) {
        return new PayoutFormManager$$Lambda$2(payoutFormField);
    }

    public boolean apply(Object obj) {
        return ((PayoutFormFieldInputWrapper) obj).payoutFormField().equals(this.arg$1);
    }
}
