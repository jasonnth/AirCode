package com.airbnb.android.lib.payments.activities;

import android.os.Bundle;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;
import com.airbnb.android.lib.payments.activities.AddCouponCodeActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AddCouponCodeActivity$$Icepick<T extends AddCouponCodeActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9623H = new Helper("com.airbnb.android.lib.payments.activities.AddCouponCodeActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.clientType = (QuickPayClientType) f9623H.getSerializable(state, "clientType");
            target.selectedPaymentOption = (PaymentOption) f9623H.getParcelable(state, "selectedPaymentOption");
            target.quickPayParameters = (QuickPayParameters) f9623H.getParcelable(state, "quickPayParameters");
            target.isCreditApplied = f9623H.getBoolean(state, "isCreditApplied");
            target.userAgreedToCurrencyMismatch = f9623H.getBoolean(state, "userAgreedToCurrencyMismatch");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9623H.putSerializable(state, "clientType", target.clientType);
        f9623H.putParcelable(state, "selectedPaymentOption", target.selectedPaymentOption);
        f9623H.putParcelable(state, "quickPayParameters", target.quickPayParameters);
        f9623H.putBoolean(state, "isCreditApplied", target.isCreditApplied);
        f9623H.putBoolean(state, "userAgreedToCurrencyMismatch", target.userAgreedToCurrencyMismatch);
    }
}
