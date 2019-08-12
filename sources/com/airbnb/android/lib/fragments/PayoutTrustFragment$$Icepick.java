package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.lib.fragments.PayoutTrustFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PayoutTrustFragment$$Icepick<T extends PayoutTrustFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9542H = new Helper("com.airbnb.android.lib.fragments.PayoutTrustFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.birthday = (AirDate) f9542H.getParcelable(state, "birthday");
            target.mPayoutCountryCode = f9542H.getString(state, "mPayoutCountryCode");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9542H.putParcelable(state, "birthday", target.birthday);
        f9542H.putString(state, "mPayoutCountryCode", target.mPayoutCountryCode);
    }
}
