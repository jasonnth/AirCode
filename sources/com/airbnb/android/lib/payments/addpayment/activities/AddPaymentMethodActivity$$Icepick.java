package com.airbnb.android.lib.payments.addpayment.activities;

import android.os.Bundle;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.lib.payments.addpayment.activities.AddPaymentMethodActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AddPaymentMethodActivity$$Icepick<T extends AddPaymentMethodActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9629H = new Helper("com.airbnb.android.lib.payments.addpayment.activities.AddPaymentMethodActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.productType = (BillProductType) f9629H.getSerializable(state, "productType");
            target.paymentOptions = f9629H.getParcelableArrayList(state, "paymentOptions");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9629H.putSerializable(state, "productType", target.productType);
        f9629H.putParcelableArrayList(state, "paymentOptions", target.paymentOptions);
    }
}
