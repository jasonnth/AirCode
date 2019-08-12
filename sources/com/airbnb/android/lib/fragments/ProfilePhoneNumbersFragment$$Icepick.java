package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.ProfilePhoneNumbersFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ProfilePhoneNumbersFragment$$Icepick<T extends ProfilePhoneNumbersFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9545H = new Helper("com.airbnb.android.lib.fragments.ProfilePhoneNumbersFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isPNRUser = f9545H.getBoolean(state, "isPNRUser");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9545H.putBoolean(state, "isPNRUser", target.isPNRUser);
    }
}
