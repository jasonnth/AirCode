package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import com.airbnb.android.explore.fragments.MTHomesTabFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class MTHomesTabFragment$$Icepick<T extends MTHomesTabFragment> extends MTTabFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8583H = new Helper("com.airbnb.android.explore.fragments.MTHomesTabFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.dummyState = f8583H.getInt(state, "dummyState");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8583H.putInt(state, "dummyState", target.dummyState);
    }
}
