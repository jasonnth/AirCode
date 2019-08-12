package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.lib.fragments.ManageGuestIdentityInfoFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageGuestIdentityInfoFragment$$Icepick<T extends ManageGuestIdentityInfoFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9539H = new Helper("com.airbnb.android.lib.fragments.ManageGuestIdentityInfoFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.identityToDelete = (GuestIdentity) f9539H.getParcelable(state, "identityToDelete");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9539H.putParcelable(state, "identityToDelete", target.identityToDelete);
    }
}
