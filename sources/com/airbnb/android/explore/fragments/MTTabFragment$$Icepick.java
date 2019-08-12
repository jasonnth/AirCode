package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import com.airbnb.android.explore.fragments.MTTabFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class MTTabFragment$$Icepick<T extends MTTabFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8584H = new Helper("com.airbnb.android.explore.fragments.MTTabFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.dummyState = f8584H.getInt(state, "dummyState");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8584H.putInt(state, "dummyState", target.dummyState);
    }
}
