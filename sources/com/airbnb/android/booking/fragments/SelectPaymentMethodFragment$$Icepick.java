package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.SelectPaymentMethodFragment;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SelectPaymentMethodFragment$$Icepick<T extends SelectPaymentMethodFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7438H = new Helper("com.airbnb.android.booking.fragments.SelectPaymentMethodFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.countryCode = f7438H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            target.hideAlipayDirect = f7438H.getBoolean(state, "hideAlipayDirect");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7438H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
        f7438H.putBoolean(state, "hideAlipayDirect", target.hideAlipayDirect);
    }
}
