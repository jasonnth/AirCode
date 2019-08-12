package com.airbnb.android.lib.payments.addpayment.activities;

import android.os.Bundle;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.lib.payments.addpayment.activities.SelectBillingCountryActivity;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SelectBillingCountryActivity$$Icepick<T extends SelectBillingCountryActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9630H = new Helper("com.airbnb.android.lib.payments.addpayment.activities.SelectBillingCountryActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.billProductType = (BillProductType) f9630H.getSerializable(state, "billProductType");
            target.countryCode = f9630H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9630H.putSerializable(state, "billProductType", target.billProductType);
        f9630H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
    }
}
