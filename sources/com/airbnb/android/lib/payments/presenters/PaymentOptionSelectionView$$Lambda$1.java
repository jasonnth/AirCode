package com.airbnb.android.lib.payments.presenters;

import com.airbnb.android.core.models.PaymentOption;
import com.google.common.base.Function;

final /* synthetic */ class PaymentOptionSelectionView$$Lambda$1 implements Function {
    private final PaymentOptionSelectionView arg$1;

    private PaymentOptionSelectionView$$Lambda$1(PaymentOptionSelectionView paymentOptionSelectionView) {
        this.arg$1 = paymentOptionSelectionView;
    }

    public static Function lambdaFactory$(PaymentOptionSelectionView paymentOptionSelectionView) {
        return new PaymentOptionSelectionView$$Lambda$1(paymentOptionSelectionView);
    }

    public Object apply(Object obj) {
        return PaymentOptionSelectionView.lambda$setPaymentOptions$0(this.arg$1, (PaymentOption) obj);
    }
}
