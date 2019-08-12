package com.airbnb.android.lib.payments.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.lib.payments.fragments.AddCvvFragment;
import com.airbnb.android.lib.payments.fragments.AddCvvFragment.SheetTheme;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AddCvvFragment$$Icepick<T extends AddCvvFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9640H = new Helper("com.airbnb.android.lib.payments.fragments.AddCvvFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paymentOptions = f9640H.getParcelableArrayList(state, "paymentOptions");
            target.selectedPaymentOption = (PaymentOption) f9640H.getParcelable(state, "selectedPaymentOption");
            target.clientType = (QuickPayClientType) f9640H.getSerializable(state, "clientType");
            target.currentTheme = (SheetTheme) f9640H.getSerializable(state, "currentTheme");
            target.totalPrice = (Price) f9640H.getParcelable(state, "totalPrice");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9640H.putParcelableArrayList(state, "paymentOptions", target.paymentOptions);
        f9640H.putParcelable(state, "selectedPaymentOption", target.selectedPaymentOption);
        f9640H.putSerializable(state, "clientType", target.clientType);
        f9640H.putSerializable(state, "currentTheme", target.currentTheme);
        f9640H.putParcelable(state, "totalPrice", target.totalPrice);
    }
}
