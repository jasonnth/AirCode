package com.airbnb.android.lib.fragments.verifications;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.verifications.EmailVerificationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class EmailVerificationFragment$$Icepick<T extends EmailVerificationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9587H = new Helper("com.airbnb.android.lib.fragments.verifications.EmailVerificationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.email = f9587H.getString(state, "email");
            target.isCurrentEmailContainerDisplayed = f9587H.getBoolean(state, "isCurrentEmailContainerDisplayed");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9587H.putString(state, "email", target.email);
        f9587H.putBoolean(state, "isCurrentEmailContainerDisplayed", target.isCurrentEmailContainerDisplayed);
    }
}
