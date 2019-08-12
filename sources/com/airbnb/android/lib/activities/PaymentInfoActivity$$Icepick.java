package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.lib.activities.PaymentInfoActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PaymentInfoActivity$$Icepick<T extends PaymentInfoActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9471H = new Helper("com.airbnb.android.lib.activities.PaymentInfoActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.address = (AirAddress) f9471H.getParcelable(state, "address");
            target.name = f9471H.getString(state, "name");
            target.payoutCurrency = f9471H.getString(state, "payoutCurrency");
            target.payoutInfoTypes = f9471H.getParcelableArrayList(state, "payoutInfoTypes");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9471H.putParcelable(state, "address", target.address);
        f9471H.putString(state, "name", target.name);
        f9471H.putString(state, "payoutCurrency", target.payoutCurrency);
        f9471H.putParcelableArrayList(state, "payoutInfoTypes", target.payoutInfoTypes);
    }
}
