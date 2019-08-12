package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.PayoutListFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PayoutListFragment$$Icepick<T extends PayoutListFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9541H = new Helper("com.airbnb.android.lib.fragments.PayoutListFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mPayoutInfo = f9541H.getParcelableArrayList(state, "mPayoutInfo");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9541H.putParcelableArrayList(state, "mPayoutInfo", target.mPayoutInfo);
    }
}
