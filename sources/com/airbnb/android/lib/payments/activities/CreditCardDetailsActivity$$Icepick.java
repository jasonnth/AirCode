package com.airbnb.android.lib.payments.activities;

import android.os.Bundle;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.lib.payments.activities.CreditCardDetailsActivity;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CreditCardDetailsActivity$$Icepick<T extends CreditCardDetailsActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9625H = new Helper("com.airbnb.android.lib.payments.activities.CreditCardDetailsActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paymentMethodType = (PaymentMethodType) f9625H.getSerializable(state, "paymentMethodType");
            target.countryCode = f9625H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9625H.putSerializable(state, "paymentMethodType", target.paymentMethodType);
        f9625H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
    }
}
