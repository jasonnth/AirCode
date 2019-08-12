package com.airbnb.android.booking.p336n2;

import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.booking.n2.PaymentInstrumentSelectionView$$Lambda$1 */
final /* synthetic */ class PaymentInstrumentSelectionView$$Lambda$1 implements Function {
    private static final PaymentInstrumentSelectionView$$Lambda$1 instance = new PaymentInstrumentSelectionView$$Lambda$1();

    private PaymentInstrumentSelectionView$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new PaymentInstrumentSelectionViewItem((OldPaymentInstrument) obj);
    }
}
