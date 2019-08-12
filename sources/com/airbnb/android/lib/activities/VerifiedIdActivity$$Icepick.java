package com.airbnb.android.lib.activities;

import android.os.Bundle;
import com.airbnb.android.lib.activities.VerifiedIdActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class VerifiedIdActivity$$Icepick<T extends VerifiedIdActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9484H = new Helper("com.airbnb.android.lib.activities.VerifiedIdActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.showSesameVerification = f9484H.getBoolean(state, "showSesameVerification");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9484H.putBoolean(state, "showSesameVerification", target.showSesameVerification);
    }
}
