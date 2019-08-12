package com.airbnb.android.lib.cancellation.host;

import android.os.Bundle;
import com.airbnb.android.lib.cancellation.host.LateCancellationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LateCancellationFragment$$Icepick<T extends LateCancellationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9511H = new Helper("com.airbnb.android.lib.cancellation.host.LateCancellationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.phoneNumbers = f9511H.getParcelableArrayList(state, "phoneNumbers");
            target.isLoading = f9511H.getBoolean(state, "isLoading");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9511H.putParcelableArrayList(state, "phoneNumbers", target.phoneNumbers);
        f9511H.putBoolean(state, "isLoading", target.isLoading);
    }
}
