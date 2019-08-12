package com.airbnb.android.lib.presenters.p338n2.paymentinfo;

import com.google.common.base.Function;

/* renamed from: com.airbnb.android.lib.presenters.n2.paymentinfo.PayoutCurrencySelectionView$$Lambda$1 */
final /* synthetic */ class PayoutCurrencySelectionView$$Lambda$1 implements Function {
    private static final PayoutCurrencySelectionView$$Lambda$1 instance = new PayoutCurrencySelectionView$$Lambda$1();

    private PayoutCurrencySelectionView$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PayoutCurrencySelectionView.lambda$setPayoutCurrencies$0((String) obj);
    }
}
