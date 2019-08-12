package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.identity.IdentitySelfieCaptureFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class IdentitySelfieCaptureFragment$$Icepick<T extends IdentitySelfieCaptureFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9179H = new Helper("com.airbnb.android.identity.IdentitySelfieCaptureFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.pendingStart = f9179H.getBoolean(state, "pendingStart");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9179H.putBoolean(state, "pendingStart", target.pendingStart);
    }
}
