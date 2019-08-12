package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;
import com.airbnb.android.lib.businesstravel.WorkEmailFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WorkEmailFragment$$Icepick<T extends WorkEmailFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9500H = new Helper("com.airbnb.android.lib.businesstravel.WorkEmailFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.email = f9500H.getString(state, "email");
            target.confirmationCode = f9500H.getString(state, "confirmationCode");
            target.launchSource = (WorkEmailLaunchSource) f9500H.getSerializable(state, "launchSource");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9500H.putString(state, "email", target.email);
        f9500H.putString(state, "confirmationCode", target.confirmationCode);
        f9500H.putSerializable(state, "launchSource", target.launchSource);
    }
}
