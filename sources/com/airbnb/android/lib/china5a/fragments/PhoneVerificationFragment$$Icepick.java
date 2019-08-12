package com.airbnb.android.lib.china5a.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.lib.china5a.fragments.PhoneVerificationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PhoneVerificationFragment$$Icepick<T extends PhoneVerificationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9518H = new Helper("com.airbnb.android.lib.china5a.fragments.PhoneVerificationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.countryCodeItem = (CountryCodeItem) f9518H.getParcelable(state, "countryCodeItem");
            target.airPhone = (AirPhone) f9518H.getParcelable(state, "airPhone");
            target.confirmationCode = f9518H.getString(state, "confirmationCode");
            target.lastRequestTime = f9518H.getLong(state, "lastRequestTime");
            target.listeningToSMS = f9518H.getBoolean(state, "listeningToSMS");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9518H.putParcelable(state, "countryCodeItem", target.countryCodeItem);
        f9518H.putParcelable(state, "airPhone", target.airPhone);
        f9518H.putString(state, "confirmationCode", target.confirmationCode);
        f9518H.putLong(state, "lastRequestTime", target.lastRequestTime);
        f9518H.putBoolean(state, "listeningToSMS", target.listeningToSMS);
    }
}
