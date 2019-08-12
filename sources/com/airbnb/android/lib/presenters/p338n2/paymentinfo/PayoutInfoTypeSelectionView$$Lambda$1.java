package com.airbnb.android.lib.presenters.p338n2.paymentinfo;

import com.airbnb.android.core.models.PayoutInfoType;
import com.google.common.base.Function;

/* renamed from: com.airbnb.android.lib.presenters.n2.paymentinfo.PayoutInfoTypeSelectionView$$Lambda$1 */
final /* synthetic */ class PayoutInfoTypeSelectionView$$Lambda$1 implements Function {
    private static final PayoutInfoTypeSelectionView$$Lambda$1 instance = new PayoutInfoTypeSelectionView$$Lambda$1();

    private PayoutInfoTypeSelectionView$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PayoutInfoTypeSelectionView.lambda$setPayoutInfoTypes$0((PayoutInfoType) obj);
    }
}
