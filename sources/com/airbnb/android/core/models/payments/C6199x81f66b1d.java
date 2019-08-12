package com.airbnb.android.core.models.payments;

import com.airbnb.android.core.models.payments.BusinessTravelPaymentInstruments.BusinessTravelPaymentMethod;
import com.google.common.base.Predicate;

/* renamed from: com.airbnb.android.core.models.payments.BusinessTravelPaymentInstruments$BusinessTravelPaymentMethod$$Lambda$1 */
final /* synthetic */ class C6199x81f66b1d implements Predicate {
    private final String arg$1;

    private C6199x81f66b1d(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new C6199x81f66b1d(str);
    }

    public boolean apply(Object obj) {
        return ((BusinessTravelPaymentMethod) obj).getServerKey().equals(this.arg$1);
    }
}
