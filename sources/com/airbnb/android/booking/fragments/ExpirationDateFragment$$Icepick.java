package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.ExpirationDateFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ExpirationDateFragment$$Icepick<T extends ExpirationDateFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7434H = new Helper("com.airbnb.android.booking.fragments.ExpirationDateFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.month = f7434H.getInt(state, "month");
            target.year = f7434H.getInt(state, "year");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7434H.putInt(state, "month", target.month);
        f7434H.putInt(state, "year", target.year);
    }
}
