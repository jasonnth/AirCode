package com.airbnb.android.lib.china5a.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.china5a.fragments.PhotoVerificationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PhotoVerificationFragment$$Icepick<T extends PhotoVerificationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9519H = new Helper("com.airbnb.android.lib.china5a.fragments.PhotoVerificationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.photoFilePath = f9519H.getString(state, "photoFilePath");
            target.photoUrl = f9519H.getString(state, "photoUrl");
            target.currentState = f9519H.getInt(state, "currentState");
            target.showLoader = f9519H.getBoolean(state, "showLoader");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9519H.putString(state, "photoFilePath", target.photoFilePath);
        f9519H.putString(state, "photoUrl", target.photoUrl);
        f9519H.putInt(state, "currentState", target.currentState);
        f9519H.putBoolean(state, "showLoader", target.showLoader);
    }
}
