package com.airbnb.android.core.host;

import android.os.Bundle;
import com.airbnb.android.core.host.HostReactivationFragment;
import com.airbnb.android.core.models.HostStandard;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostReactivationFragment$$Icepick<T extends HostReactivationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8457H = new Helper("com.airbnb.android.core.host.HostReactivationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hostStandard = (HostStandard) f8457H.getParcelable(state, "hostStandard");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8457H.putParcelable(state, "hostStandard", target.hostStandard);
    }
}
