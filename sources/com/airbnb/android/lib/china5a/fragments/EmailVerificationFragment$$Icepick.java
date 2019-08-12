package com.airbnb.android.lib.china5a.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.china5a.fragments.EmailVerificationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class EmailVerificationFragment$$Icepick<T extends EmailVerificationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9517H = new Helper("com.airbnb.android.lib.china5a.fragments.EmailVerificationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.sendClicked = f9517H.getBoolean(state, "sendClicked");
            target.emailSent = f9517H.getBoolean(state, "emailSent");
            target.emailAddress = f9517H.getString(state, "emailAddress");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9517H.putBoolean(state, "sendClicked", target.sendClicked);
        f9517H.putBoolean(state, "emailSent", target.emailSent);
        f9517H.putString(state, "emailAddress", target.emailAddress);
    }
}
