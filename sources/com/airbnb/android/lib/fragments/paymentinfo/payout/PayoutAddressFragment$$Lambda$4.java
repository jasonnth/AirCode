package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.text.TextUtils;
import com.airbnb.p027n2.components.SheetInputText;
import com.google.common.base.Predicate;

final /* synthetic */ class PayoutAddressFragment$$Lambda$4 implements Predicate {
    private static final PayoutAddressFragment$$Lambda$4 instance = new PayoutAddressFragment$$Lambda$4();

    private PayoutAddressFragment$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return TextUtils.isEmpty(((SheetInputText) obj).getText());
    }
}
