package com.airbnb.android.lib.payments.addpayment.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.lib.payments.addpayment.fragments.AddPaymentMethodFragment;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AddPaymentMethodFragment$$Icepick<T extends AddPaymentMethodFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9632H = new Helper("com.airbnb.android.lib.payments.addpayment.fragments.AddPaymentMethodFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.billProductType = (BillProductType) f9632H.getSerializable(state, "billProductType");
            target.paymentOptions = f9632H.getParcelableArrayList(state, "paymentOptions");
            target.paymentInstrument = (OldPaymentInstrument) f9632H.getSerializable(state, "paymentInstrument");
            target.countryCode = f9632H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            target.isLoading = f9632H.getBoolean(state, "isLoading");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9632H.putSerializable(state, "billProductType", target.billProductType);
        f9632H.putParcelableArrayList(state, "paymentOptions", target.paymentOptions);
        f9632H.putSerializable(state, "paymentInstrument", target.paymentInstrument);
        f9632H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
        f9632H.putBoolean(state, "isLoading", target.isLoading);
    }
}
