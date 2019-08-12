package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.android.core.models.PayoutInfoType;
import com.google.common.base.Predicate;

final /* synthetic */ class PayoutInfoTypesFragment$$Lambda$1 implements Predicate {
    private final PayoutInfoTypesFragment arg$1;

    private PayoutInfoTypesFragment$$Lambda$1(PayoutInfoTypesFragment payoutInfoTypesFragment) {
        this.arg$1 = payoutInfoTypesFragment;
    }

    public static Predicate lambdaFactory$(PayoutInfoTypesFragment payoutInfoTypesFragment) {
        return new PayoutInfoTypesFragment$$Lambda$1(payoutInfoTypesFragment);
    }

    public boolean apply(Object obj) {
        return this.arg$1.acceptablePayoutTypes.contains(((PayoutInfoType) obj).getInfoType());
    }
}
