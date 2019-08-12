package com.airbnb.android.login.smartlock;

import android.os.Bundle;
import com.airbnb.android.login.smartlock.GoogleSmartLockControllerImpl;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class GoogleSmartLockControllerImpl$$Icepick<T extends GoogleSmartLockControllerImpl> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10052H = new Helper("com.airbnb.android.login.smartlock.GoogleSmartLockControllerImpl$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isResolving = f10052H.getBoolean(state, "isResolving");
            target.hasRequestedCredential = f10052H.getBoolean(state, "hasRequestedCredential");
            target.isRequestingCredential = f10052H.getBoolean(state, "isRequestingCredential");
            target.ignoreCredentialResponse = f10052H.getBoolean(state, "ignoreCredentialResponse");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10052H.putBoolean(state, "isResolving", target.isResolving);
        f10052H.putBoolean(state, "hasRequestedCredential", target.hasRequestedCredential);
        f10052H.putBoolean(state, "isRequestingCredential", target.isRequestingCredential);
        f10052H.putBoolean(state, "ignoreCredentialResponse", target.ignoreCredentialResponse);
    }
}
