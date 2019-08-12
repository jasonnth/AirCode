package com.airbnb.android.lib.fragments.verifiedid;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.verifiedid.OfflineIdChildFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class OfflineIdChildFragment$$Icepick<T extends OfflineIdChildFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9591H = new Helper("com.airbnb.android.lib.fragments.verifiedid.OfflineIdChildFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.showSesameCreditSwitcher = f9591H.getBoolean(state, "showSesameCreditSwitcher");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9591H.putBoolean(state, "showSesameCreditSwitcher", target.showSesameCreditSwitcher);
    }
}
