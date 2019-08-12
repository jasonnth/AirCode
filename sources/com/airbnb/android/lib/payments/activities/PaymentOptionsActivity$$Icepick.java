package com.airbnb.android.lib.payments.activities;

import android.os.Bundle;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.payments.activities.PaymentOptionsActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PaymentOptionsActivity$$Icepick<T extends PaymentOptionsActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9626H = new Helper("com.airbnb.android.lib.payments.activities.PaymentOptionsActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paymentOptions = f9626H.getParcelableArrayList(state, "paymentOptions");
            target.selectedPaymentOption = (PaymentOption) f9626H.getParcelable(state, "selectedPaymentOption");
            target.clientType = (QuickPayClientType) f9626H.getSerializable(state, "clientType");
            target.totalPrice = (Price) f9626H.getParcelable(state, "totalPrice");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9626H.putParcelableArrayList(state, "paymentOptions", target.paymentOptions);
        f9626H.putParcelable(state, "selectedPaymentOption", target.selectedPaymentOption);
        f9626H.putSerializable(state, "clientType", target.clientType);
        f9626H.putParcelable(state, "totalPrice", target.totalPrice);
    }
}
