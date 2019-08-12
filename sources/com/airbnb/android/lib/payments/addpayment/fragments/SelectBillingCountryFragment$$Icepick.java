package com.airbnb.android.lib.payments.addpayment.fragments;

import android.os.Bundle;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.lib.payments.addpayment.fragments.SelectBillingCountryFragment;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SelectBillingCountryFragment$$Icepick<T extends SelectBillingCountryFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9633H = new Helper("com.airbnb.android.lib.payments.addpayment.fragments.SelectBillingCountryFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.billProductType = (BillProductType) f9633H.getSerializable(state, "billProductType");
            target.countryCode = f9633H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9633H.putSerializable(state, "billProductType", target.billProductType);
        f9633H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
    }
}
