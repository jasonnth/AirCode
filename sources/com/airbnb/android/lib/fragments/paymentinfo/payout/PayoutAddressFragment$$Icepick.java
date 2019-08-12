package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutAddressFragment;
import com.braintreepayments.api.models.PostalAddress;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PayoutAddressFragment$$Icepick<T extends PayoutAddressFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9578H = new Helper("com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutAddressFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.countryCode = f9578H.getString(state, PostalAddress.COUNTRY_CODE_KEY);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9578H.putString(state, PostalAddress.COUNTRY_CODE_KEY, target.countryCode);
    }
}
