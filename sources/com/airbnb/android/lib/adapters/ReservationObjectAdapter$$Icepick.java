package com.airbnb.android.lib.adapters;

import android.os.Bundle;
import com.airbnb.android.lib.adapters.ReservationObjectAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ReservationObjectAdapter$$Icepick<T extends ReservationObjectAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9491H = new Helper("com.airbnb.android.lib.adapters.ReservationObjectAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.shouldLogDetails = f9491H.getBoolean(state, "shouldLogDetails");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9491H.putBoolean(state, "shouldLogDetails", target.shouldLogDetails);
    }
}
