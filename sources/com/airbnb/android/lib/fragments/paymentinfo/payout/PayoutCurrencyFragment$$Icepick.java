package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import com.airbnb.android.core.models.PayoutInfoType;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutCurrencyFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PayoutCurrencyFragment$$Icepick<T extends PayoutCurrencyFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9579H = new Helper("com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutCurrencyFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.payoutInfoType = (PayoutInfoType) f9579H.getParcelable(state, "payoutInfoType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9579H.putParcelable(state, "payoutInfoType", target.payoutInfoType);
    }
}
