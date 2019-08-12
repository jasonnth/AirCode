package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.core.data.AddressParts;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LegacyAddPayoutActivity$$Icepick<T extends LegacyAddPayoutActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9468H = new Helper("com.airbnb.android.lib.activities.LegacyAddPayoutActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mCountryCode = f9468H.getString(state, "mCountryCode");
            target.mSupportedCurrencies = f9468H.getStringArrayList(state, "mSupportedCurrencies");
            target.addressParts = (AddressParts) f9468H.getParcelable(state, "addressParts");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9468H.putString(state, "mCountryCode", target.mCountryCode);
        f9468H.putStringArrayList(state, "mSupportedCurrencies", target.mSupportedCurrencies);
        f9468H.putParcelable(state, "addressParts", target.addressParts);
    }
}
