package com.airbnb.android.booking.activities;

import android.os.Bundle;
import com.airbnb.android.booking.activities.AlipayV2Activity;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.utils.ParcelStrap;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AlipayV2Activity$$Icepick<T extends AlipayV2Activity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7417H = new Helper("com.airbnb.android.booking.activities.AlipayV2Activity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.analyticsData = (ParcelStrap) f7417H.getParcelable(state, "analyticsData");
            target.isQuickPay = f7417H.getBoolean(state, "isQuickPay");
            target.paymentInstrument = (PaymentInstrument) f7417H.getParcelable(state, "paymentInstrument");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7417H.putParcelable(state, "analyticsData", target.analyticsData);
        f7417H.putBoolean(state, "isQuickPay", target.isQuickPay);
        f7417H.putParcelable(state, "paymentInstrument", target.paymentInstrument);
    }
}
