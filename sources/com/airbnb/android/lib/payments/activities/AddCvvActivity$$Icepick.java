package com.airbnb.android.lib.payments.activities;

import android.os.Bundle;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.payments.activities.AddCvvActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AddCvvActivity$$Icepick<T extends AddCvvActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9624H = new Helper("com.airbnb.android.lib.payments.activities.AddCvvActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paymentOption = f9624H.getParcelableArrayList(state, "paymentOption");
            target.selectedPaymentOption = (PaymentOption) f9624H.getParcelable(state, "selectedPaymentOption");
            target.clientType = (QuickPayClientType) f9624H.getSerializable(state, "clientType");
            target.totalPrice = (Price) f9624H.getParcelable(state, "totalPrice");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9624H.putParcelableArrayList(state, "paymentOption", target.paymentOption);
        f9624H.putParcelable(state, "selectedPaymentOption", target.selectedPaymentOption);
        f9624H.putSerializable(state, "clientType", target.clientType);
        f9624H.putParcelable(state, "totalPrice", target.totalPrice);
    }
}
