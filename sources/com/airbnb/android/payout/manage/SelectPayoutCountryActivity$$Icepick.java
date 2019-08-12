package com.airbnb.android.payout.manage;

import android.os.Bundle;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.payout.manage.SelectPayoutCountryActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SelectPayoutCountryActivity$$Icepick<T extends SelectPayoutCountryActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10773H = new Helper("com.airbnb.android.payout.manage.SelectPayoutCountryActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.countryCodeItem = (CountryCodeItem) f10773H.getParcelable(state, "countryCodeItem");
            target.newPayoutSupportingCountries = f10773H.getParcelableArrayList(state, "newPayoutSupportingCountries");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10773H.putParcelable(state, "countryCodeItem", target.countryCodeItem);
        f10773H.putParcelableArrayList(state, "newPayoutSupportingCountries", target.newPayoutSupportingCountries);
    }
}
