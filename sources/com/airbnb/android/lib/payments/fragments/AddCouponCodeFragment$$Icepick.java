package com.airbnb.android.lib.payments.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.airbnb.android.lib.payments.fragments.AddCouponCodeFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AddCouponCodeFragment$$Icepick<T extends AddCouponCodeFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9639H = new Helper("com.airbnb.android.lib.payments.fragments.AddCouponCodeFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.clientType = (QuickPayClientType) f9639H.getSerializable(state, "clientType");
            target.selectedPaymentOption = (PaymentOption) f9639H.getParcelable(state, "selectedPaymentOption");
            target.quickPayParameters = (QuickPayParameters) f9639H.getParcelable(state, "quickPayParameters");
            target.isCreditApplied = f9639H.getBoolean(state, "isCreditApplied");
            target.userAgreedToCurrencyMismatch = f9639H.getBoolean(state, "userAgreedToCurrencyMismatch");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9639H.putSerializable(state, "clientType", target.clientType);
        f9639H.putParcelable(state, "selectedPaymentOption", target.selectedPaymentOption);
        f9639H.putParcelable(state, "quickPayParameters", target.quickPayParameters);
        f9639H.putBoolean(state, "isCreditApplied", target.isCreditApplied);
        f9639H.putBoolean(state, "userAgreedToCurrencyMismatch", target.userAgreedToCurrencyMismatch);
    }
}
