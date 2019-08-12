package com.airbnb.android.booking.p336n2;

import com.airbnb.android.core.enums.PaymentMethod;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.booking.n2.PaymentMethodSelectionView$$Lambda$1 */
final /* synthetic */ class PaymentMethodSelectionView$$Lambda$1 implements Function {
    private final PaymentMethodSelectionView arg$1;

    private PaymentMethodSelectionView$$Lambda$1(PaymentMethodSelectionView paymentMethodSelectionView) {
        this.arg$1 = paymentMethodSelectionView;
    }

    public static Function lambdaFactory$(PaymentMethodSelectionView paymentMethodSelectionView) {
        return new PaymentMethodSelectionView$$Lambda$1(paymentMethodSelectionView);
    }

    public Object apply(Object obj) {
        return PaymentMethodSelectionView.lambda$setPaymentMethods$0(this.arg$1, (PaymentMethod) obj);
    }
}
