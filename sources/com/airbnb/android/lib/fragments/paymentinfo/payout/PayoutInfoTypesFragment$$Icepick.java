package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutInfoTypesFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PayoutInfoTypesFragment$$Icepick<T extends PayoutInfoTypesFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9580H = new Helper("com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutInfoTypesFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.payoutInfoTypes = f9580H.getParcelableArrayList(state, "payoutInfoTypes");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9580H.putParcelableArrayList(state, "payoutInfoTypes", target.payoutInfoTypes);
    }
}
