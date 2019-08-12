package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.identity.AccountVerificationPhoneNumberInputFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AccountVerificationPhoneNumberInputFragment$$Icepick<T extends AccountVerificationPhoneNumberInputFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9170H = new Helper("com.airbnb.android.identity.AccountVerificationPhoneNumberInputFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.countryCodeItem = (CountryCodeItem) f9170H.getParcelable(state, "countryCodeItem");
            target.airPhone = (AirPhone) f9170H.getParcelable(state, "airPhone");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9170H.putParcelable(state, "countryCodeItem", target.countryCodeItem);
        f9170H.putParcelable(state, "airPhone", target.airPhone);
    }
}
