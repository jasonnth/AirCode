package com.airbnb.android.lib.identity.psb;

import android.os.Bundle;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.lib.identity.psb.KonaSelectGuestProfileFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class KonaSelectGuestProfileFragment$$Icepick<T extends KonaSelectGuestProfileFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9601H = new Helper("com.airbnb.android.lib.identity.psb.KonaSelectGuestProfileFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.preselectedIdentifications = f9601H.getParcelableArrayList(state, "preselectedIdentifications");
            target.isBookerIdentification = f9601H.getBoolean(state, "isBookerIdentification");
            target.newIdentity = (GuestIdentity) f9601H.getParcelable(state, "newIdentity");
            target.isP4Redesign = f9601H.getBoolean(state, "isP4Redesign");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9601H.putParcelableArrayList(state, "preselectedIdentifications", target.preselectedIdentifications);
        f9601H.putBoolean(state, "isBookerIdentification", target.isBookerIdentification);
        f9601H.putParcelable(state, "newIdentity", target.newIdentity);
        f9601H.putBoolean(state, "isP4Redesign", target.isP4Redesign);
    }
}
