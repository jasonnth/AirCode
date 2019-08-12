package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.KonaHouseManualFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class KonaHouseManualFragment$$Icepick<T extends KonaHouseManualFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9536H = new Helper("com.airbnb.android.lib.fragments.KonaHouseManualFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.houseManual = f9536H.getString(state, "houseManual");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9536H.putString(state, "houseManual", target.houseManual);
    }
}
